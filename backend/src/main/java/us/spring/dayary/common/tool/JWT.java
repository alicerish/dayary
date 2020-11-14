package us.spring.dayary.common.tool;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import us.spring.dayary.domain.Auth;
import us.spring.dayary.domain.Member;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

public class JWT {

    public static String make(Member member, String _jwtKey) {
        Long currentTimeMillis = System.currentTimeMillis();
        try {
            // TODO 공통 처리
            Algorithm algorithm = Algorithm.HMAC256(_jwtKey);
            String _jwt = com.auth0.jwt.JWT.create()
                    .withClaim("seq", member.getSeq())
                    .withClaim("dateOfIssue", currentTimeMillis)
                    .withClaim("dateOfExpiry", currentTimeMillis + 900000L)//15분
                    .sign(algorithm);
            return _jwt;
        } catch (JWTCreationException exception) {
            throw exception;
        }
    }

    public static boolean verify(String _jwt, String _jwtKey) {
        try {
            // TODO 공통 처리
            Algorithm algorithm = Algorithm.HMAC256(_jwtKey);
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).build();
            verifier.verify(_jwt);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public static Map<String, Object> get(String _jwt) throws IOException {
        DecodedJWT jwt = com.auth0.jwt.JWT.decode(_jwt);
        String decodedPayLoad = new String(Base64.getDecoder().decode(jwt.getPayload().getBytes()));
        return new ObjectMapper().readValue(decodedPayLoad, Map.class);
    }

    public static Auth getAuth(String _jwt) throws IOException {
        DecodedJWT jwt = com.auth0.jwt.JWT.decode(_jwt);
        String decodedPayLoad = new String(Base64.getDecoder().decode(jwt.getPayload().getBytes()));
        return new ObjectMapper().readValue(decodedPayLoad, Auth.class);
    }
}
