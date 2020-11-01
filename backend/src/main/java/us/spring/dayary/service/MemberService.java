package us.spring.dayary.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.spring.dayary.common.exception.DayaryException;
import us.spring.dayary.common.tool.BCRYPT;
import us.spring.dayary.domain.Member;
import us.spring.dayary.domain.Status;
import us.spring.dayary.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

    @Value("${_jwt.key}")
    private String jwtKey;

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Map<String, Object> signUp(Member member) {
        Map<String, Object> data = new HashMap<>();

        //유효성검사
        if(member.getId() == null ||  member.getId().isBlank()){
            throw new DayaryException(Status.EMPTY_STRING);
        }

        if(member.getPassword() == null ||  member.getPassword().isBlank()){
            throw new DayaryException(Status.EMPTY_STRING);
        }

        // 아이디 중복 체크
        Member memberByDb = memberRepository.findById(member.getId());
        if(memberByDb != null){
            throw new DayaryException(Status.EXIST_ID);
        }

        member.setPassword(BCRYPT.hashpw(member.getPassword()));

        member.setRegDate(System.currentTimeMillis());
        memberRepository.save(member);
        return data;
    }
}
