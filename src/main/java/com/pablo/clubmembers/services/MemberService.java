package com.pablo.clubmembers.services;

import com.pablo.clubmembers.models.Book;
import com.pablo.clubmembers.models.Member;
import com.pablo.clubmembers.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepo;

    public Member create(Member m) {
        return memberRepo.save(m);
    }
    public List<Member> findAll() {
        return memberRepo.findAll();
    }
    public Member findById(Long id) {
        Optional<Member> optMember = memberRepo.findById(id);
        if (optMember.isEmpty()) {
            return null;
        }
        return optMember.get();
    }
    public Member update(Member m){
        return memberRepo.save(m);
    }
    public void delete(Long id) {
        memberRepo.deleteById(id);
    }


}
