package us.spring.dayary.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import us.spring.dayary.domain.Auth;
import us.spring.dayary.domain.Member;
import us.spring.dayary.service.MemberService;

import java.util.Map;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/logIn")
    public Map<String, Object> logIn(@RequestBody Member member) {
        return memberService.logIn(member);
    }

    @PostMapping("/signUp")
    public Map<String, Object> signUp(@RequestBody Member member) {
        return memberService.signUp(member);
    }

    @PostMapping("/test")
    public Map<String, Object> test(@RequestBody Member member, Auth auth) {
        return memberService.signUp(member);
    }
}
