package us.spring.dayary.controller;

import org.springframework.web.bind.annotation.RestController;
import us.spring.dayary.service.MemberService;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
