package com.pablo.clubmembers.controllers;

import com.pablo.clubmembers.models.Book;
import com.pablo.clubmembers.models.Member;
import com.pablo.clubmembers.services.BookService;
import com.pablo.clubmembers.services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberServ;

    @Autowired
    private BookService bookServ;

    @GetMapping("/member/dashboard")
    public String getMember(Model model) {
        // retrieve all members from DB
        List<Member> allMembers = memberServ.findAll();
        // Store in list model
        model.addAttribute("allMembers",  allMembers);
        return "memberDash.jsp";
    }

    @GetMapping("/member/new")
    public String newMember(@ModelAttribute("member")Member newMember ) {

        return "newMember.jsp";
    }

    @PostMapping("/member/new")
    public String createMember(@Valid @ModelAttribute("member")Member newMember, BindingResult result) {
        if (result.hasErrors()) {
            return "newMember.jsp";
        }
        memberServ.create(newMember);
        return "redirect:/member/dashboard";
    }

    @GetMapping("/member/{id}")
    public String showMember(@PathVariable("id") Long id, Model model) {
        model.addAttribute("member", memberServ.findById(id));
        model.addAttribute("allBooks", bookServ.findAll());
        return "viewMember.jsp";
    }
    @PostMapping("/member/{id}/checkout")
    public String checkout(@PathVariable("id") Long memberId, @RequestParam(value="book") Long bookId) {
        Member member = memberServ.findById(memberId);
        Book book = bookServ.findOneById(bookId);
        book.setMember(member);
        bookServ.create(book);
        return "redirect:/member/" + member.getId();
    }

    @GetMapping("/member/{id}/edit")
    public String editMember(@PathVariable("id")Long id, Model model) {
        model.addAttribute("member", memberServ.findById(id));
        return "editMember.jsp";
    }
    @PutMapping("/member/{id}/edit")
    // this has to come in this order valid, modelAtt etc...
    public String updateMember(
            @Valid @ModelAttribute("member") Member member, BindingResult result, @PathVariable("id") Long id)  {
        System.out.println("this is a test test test test");
        if (result.hasErrors()) {
            return "editMember.jsp";
        }
        member.setId(id);
        memberServ.update(member);
        return "redirect:/member/dashboard";
    }
//    @PostMapping("/member/{id}/edit")
//    // this has to come in this order valid, modelAtt etc...
//    public String updateMember(
//            @Valid @ModelAttribute("member") Member member, BindingResult result, @PathVariable("id") Long id)  {
//        System.out.println("this is a test test test test");
//        if (result.hasErrors()) {
//            return "editMember.jsp";
//        }
//        member.setId(id);
//        memberServ.update(member);
//        return "redirect:/member/dashboard";
//    }

    @GetMapping("/member/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id ) {
        memberServ.delete(id);
        return "redirect:/member/dashboard";
    }
}
