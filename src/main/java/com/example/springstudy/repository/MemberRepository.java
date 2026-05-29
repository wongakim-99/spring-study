package com.example.springstudy.repository;

import com.example.springstudy.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    Iterable<Member> findAll();
}
