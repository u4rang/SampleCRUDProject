package com.csf.member.service;

import com.csf.member.model.Member;
import com.csf.member.repository.MemberRepository;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void setUp() {
        Member member = new Member();
        member.setName("IMSI");
        member.setAge(99);

        memberRepository.save(member);
    }

    @Test
    public void joinTest() {
        // given
        Member member = new Member();
        member.setName("Teddy");
        member.setAge(40);

        Member savedMember = memberRepository.save(member);

        // when
        Member retrivedMember = memberRepository.findById(savedMember.getId()).get();

        // then
        Assert.assertEquals(retrivedMember.getId(), savedMember.getId());
        Assert.assertEquals(retrivedMember.getName(), savedMember.getName());
        Assert.assertEquals(retrivedMember.getAge(), savedMember.getAge());
    }

    @Test
    public void findAllTest() {
        // given

        // when
        List<Member> list = memberRepository.findAll();

        // then
        Assert.assertNotNull(list);
    }
}
