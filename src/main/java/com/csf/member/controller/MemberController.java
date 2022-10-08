package com.csf.member.controller;

import com.csf.common.BasicResponse;
import com.csf.member.model.Member;
import com.csf.member.service.MemberService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<BasicResponse> find(@PathVariable("id") Long id) {
        log.debug("[GET]/member/{}", id);
        BasicResponse basicResponse;

        Optional<Member> member = memberService.findById(id);

        if (member.isPresent()) {
            basicResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("사용자 조회 성공")
                .result(Arrays.asList(member.get()))
                .count(1)
                .build();
        } else {
            basicResponse = BasicResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("사용자 조회 실패")
                .result(Collections.emptyList())
                .count(0)
                .build();
        }

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @GetMapping("/all")
    public ResponseEntity<BasicResponse> list() {
        log.debug("[GET]/member/all");

        List<Member> list = memberService.findAll();
        BasicResponse basicResponse = BasicResponse.builder()
            .code(HttpStatus.OK.value())
            .httpStatus(HttpStatus.OK)
            .message("전체 사용자 조회 성공")
            .result(list)
            .count(list.size())
            .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @PostMapping("")
    public ResponseEntity<BasicResponse> join(@RequestBody Member member) {
        log.debug("[POST]/member, name={} age={}", member.getName(), member.getAge());
        Long id = memberService.join(member);

        BasicResponse basicResponse = BasicResponse.builder()
            .code(HttpStatus.CREATED.value())
            .httpStatus(HttpStatus.CREATED)
            .message("사용자 저장 성공")
            .result(Arrays.asList(member))
            .count(1)
            .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BasicResponse> modify(@PathVariable("id") Long id, @RequestBody Member member){
        log.debug("[PATCH]/member, name={} age={}", member.getName(), member.getAge());

        Member modifiedMember = memberService.modifyById(id, member);

        BasicResponse basicResponse = BasicResponse.builder()
            .code(HttpStatus.OK.value())
            .httpStatus(HttpStatus.CREATED)
            .message("사용자 변경 성공")
            .result(Arrays.asList(modifiedMember))
            .count(1)
            .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }
}
