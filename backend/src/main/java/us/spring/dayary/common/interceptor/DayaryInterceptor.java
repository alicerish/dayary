package us.spring.dayary.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import us.spring.dayary.common.exception.DayaryException;
import us.spring.dayary.common.filter.DayaryRequestWrapper;
import us.spring.dayary.common.tool.JWT;
import us.spring.dayary.domain.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class DayaryInterceptor extends HandlerInterceptorAdapter {

    @Value("${_jwt.key}")
    private String jwtKey;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

        String body = new String(((DayaryRequestWrapper) req).getBody());
        Map<String, Object> bodyMap = new ObjectMapper().readValue(body, Map.class);
        String _jwt = (String) bodyMap.get("_jwt");

        //jwt 없음
        if (_jwt == null) {
            throw new DayaryException(Status.INVALID_PARAMETER);
        }

        //dateOfExpiry
        if (JWT.verify(_jwt, jwtKey)) {
            Map<String, Object> payloadMap = JWT.get(_jwt);
            long dateOfExpiry = (Long) payloadMap.get("dateOfExpiry");
            if ((dateOfExpiry - System.currentTimeMillis()) < 0) { //만료된토큰
                throw new DayaryException(Status.EXPIRED_TOKEN);
            }
            return true;
        } else { //비정상토큰
            throw new DayaryException(Status.INVALID_TOKEN);
        }
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {

    }
}