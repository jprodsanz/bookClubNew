package com.pablo.clubmembers.repositories;


import com.pablo.clubmembers.models.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long>  {
    public List<Member> findAll();
}
