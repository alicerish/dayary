package us.spring.dayary.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.spring.dayary.common.exception.DayaryException;
import us.spring.dayary.common.tool.BCRYPT;
import us.spring.dayary.common.tool.JWT;
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

    public Map<String, Object> logIn(Member member) {
        Map<String, Object> data = new HashMap<>();

        //유효성검사
        // 아이디
        if (member.getId() == null || member.getId().isBlank()) {
            throw new DayaryException(Status.EMPTY_STRING);
        }
        // 비밀번호
        if (member.getPassword() == null || member.getPassword().isBlank()) {
            throw new DayaryException(Status.EMPTY_STRING);
        }
        Member memberByDb = memberRepository.findById(member.getId());
        //아이디 존재 여부
        if (memberByDb == null) {
            throw new DayaryException(Status.INVAILD_LOGIN_INFO);
        }
        //비밀번호 일치 여부
        if (!BCRYPT.checkpw(member.getPassword(), memberByDb.getPassword())) {
            throw new DayaryException(Status.INVAILD_LOGIN_INFO);
        }

        //JWT 생성
        String _jwt = JWT.make(memberByDb, jwtKey);
        //signature 추출
        String signature = _jwt.substring(_jwt.lastIndexOf(".") + 1);
        //signature DB 업데이트
        memberByDb.setSignature(signature);
        memberRepository.save(memberByDb);

        data.put("_jwt", _jwt);
        return data;
    }

    public Map<String, Object> signUp(Member member) {
        Map<String, Object> data = new HashMap<>();

        //유효성검사
        if (member.getId() == null || member.getId().isBlank()) {
            throw new DayaryException(Status.EMPTY_STRING);
        }

        if (member.getPassword() == null || member.getPassword().isBlank()) {
            throw new DayaryException(Status.EMPTY_STRING);
        }

        // 아이디 중복 체크
        Member memberByDb = memberRepository.findById(member.getId());
        if (memberByDb != null) {
            throw new DayaryException(Status.EXIST_ID);
        }

        member.setPassword(BCRYPT.hashpw(member.getPassword()));

        member.setRegDate(System.currentTimeMillis());
        memberRepository.save(member);
        return data;
    }
}
