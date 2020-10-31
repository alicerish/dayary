package us.spring.dayary.service;

import org.springframework.stereotype.Service;
import us.spring.dayary.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
