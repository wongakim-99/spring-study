package com.wongakim.springstudy.repository;

import com.wongakim.springstudy.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    Iterable<Member> findAll();
}
