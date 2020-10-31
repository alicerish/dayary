package us.spring.dayary.common.tool;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class JWT {

    public static String make(Map<String, Object> data, String _jwtKey) {
        Long currentTimeMillis = System.currentTimeMillis();
        List<String> roles = (List<String>) data.get("roles");
        String[] array = roles.toArray(new String[roles.size()]);
        try {
            Algorithm algorithm = Algorithm.HMAC256(_jwtKey);
            String _jwt = com.auth0.jwt.JWT.create()
                    .withClaim("peopleId", (String) data.get("peopleId"))
                    .withArrayClaim("roles", array)
                    .withClaim("dateOfIssue", currentTimeMillis)
                    .withClaim("dataOfExpiry", currentTimeMillis + 900000L)//15분
                    .sign(algorithm);
            return _jwt;
        } catch (JWTCreationException exception) {
            return "-1";
        }
    }

    public static boolean verify(String _jwt, String _jwtKey) {
        try {
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
}
