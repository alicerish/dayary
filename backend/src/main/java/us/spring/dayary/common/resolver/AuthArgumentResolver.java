package us.spring.dayary.common.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import us.spring.dayary.common.filter.DayaryRequestWrapper;
import us.spring.dayary.common.tool.JWT;
import us.spring.dayary.domain.Auth;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == Auth.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String body = new String(((DayaryRequestWrapper) httpServletRequest).getBody());

        String _jwt = (String) new ObjectMapper().readValue(body, Map.class).get("_jwt");

        Auth auth = JWT.getAuth(_jwt);
        auth.setSignature(_jwt.substring(_jwt.lastIndexOf(".") + 1));
        return auth;
    }
}
