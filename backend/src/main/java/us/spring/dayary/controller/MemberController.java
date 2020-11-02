package us.spring.dayary.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import us.spring.dayary.domain.Member;
import us.spring.dayary.service.MemberService;

import java.util.Map;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signUp")
    public Map<String, Object> signUp(@RequestBody Member member) {
        return memberService.signUp(member);
    }
}
