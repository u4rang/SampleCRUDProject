package com.csf.member.service;

import com.csf.member.model.Member;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long join(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    Member modifyById(Long id, Member member);
}
