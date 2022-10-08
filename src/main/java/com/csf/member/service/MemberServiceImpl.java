package com.csf.member.service;

import com.csf.member.model.Member;
import com.csf.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long join(Member member) {
        return memberRepository.save(member).getId();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member modifyById(Long id, Member member) {
        Optional<Member> updatedMember = memberRepository.findById(id);

        updatedMember.ifPresent(selectId -> {
            if(member.getName() != null && !member.getName().equals(selectId.getName())) {
                selectId.setName(member.getName());
            }

            if(member.getAge() != 0 && member.getAge() != selectId.getAge()) {
                selectId.setAge(member.getAge());
            }

            memberRepository.save(selectId);
        });

        return updatedMember.get();
    }
}
