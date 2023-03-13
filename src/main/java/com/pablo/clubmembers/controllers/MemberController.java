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
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberServ;

    @Autowired
    private BookService bookServ;

    @GetMapping("/dashboard")
    public String getMember(Model model) {
        // retrieve all members from DB
        List<Member> allMembers = memberServ.findAll();
        // Store in list model
        model.addAttribute("allMembers",  allMembers);
        return "memberDash.jsp";
    }

    @GetMapping("/new")
    public String newMember(@ModelAttribute("member")Member newMember ) {

        return "newMember.jsp";
    }

    @PostMapping("/new")
    public String createMember(@Valid @ModelAttribute("member")Member newMember, BindingResult result) {
        if (result.hasErrors()) {
            return "newMember.jsp";
        }
        memberServ.create(newMember);
        return "redirect:/member/dashboard";
    }

    @GetMapping("/{id}")
    public String showMember(@PathVariable("id") Long id, Model model) {
        model.addAttribute("member", memberServ.findById(id));
        model.addAttribute("allBooks", bookServ.findAll());
        return "viewMember.jsp";
    }
    @PostMapping("/{id}/checkout")
    public String checkout(@PathVariable("id") Long memberId, @RequestParam(value="book") Long bookId) {
        Member member = memberServ.findById(memberId);
        Book book = bookServ.findOneById(bookId);
        book.setMember(member);
        bookServ.create(book);
        return "redirect:/member/" + member.getId();
    }
    @GetMapping("/member/{id}/delete")
    public String deleteMember(@PathVariable("id") Long id ) {
        memberServ.delete(id);
        return "redirect:/member/dashboard";
    }
}
