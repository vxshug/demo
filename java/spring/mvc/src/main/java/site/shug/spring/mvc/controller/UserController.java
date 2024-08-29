package site.shug.spring.mvc.controller;

import com.nimbusds.jose.JWSHeader;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import site.shug.spring.mvc.request.UserRequest;
import site.shug.spring.mvc.utils.JwtUtils;

import java.util.*;

/**
 * 默认Spring MVC是不json的
 */

@RestController
public class UserController {
    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    @Qualifier("i18n")
    MessageSource messageSource;

    @Autowired
    AuthenticationManager authenticationManager;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody UserRequest user) {

        UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.unauthenticated(user.name, user.password);
        Authentication authenticated = authenticationManager.authenticate(authToken);
        SecurityContext context = securityContextHolderStrategy.getContext();
        context.setAuthentication(authenticated);
        Collection<? extends GrantedAuthority> collection = authenticated.getAuthorities();
        Optional<String> string = collection.stream().map(Object::toString).reduce((p, v) -> p + ";" + v);
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject("anli")
                .claim("name", user.name)
                .claim("ah", string.orElse(""))
                .id("123")
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwtClaimsSet);
        String jwt = JwtUtils.encodeJwt(jwtEncoderParameters);
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", user.name);
        result.put("token", jwt);
        return result;
    }

    @GetMapping("/callback")
    public Map<String, String[]> callback(HttpServletRequest request) {
        return request.getParameterMap();
    }
    @GetMapping("/say")
    public String sayHello(HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);
        return messageSource.getMessage("say", null, locale);
    }

    @GetMapping("/ex")
    public void ex() {
        throw new RuntimeException("产生异常");
    }
    /**
     * 仅可以捕获当前Controller的异常
     * @param e 没有固定参数可以传入Exception, HttpServletRequest等,
     * 返回值可以是void, ModelAndView
     */
    @ExceptionHandler(RuntimeException.class)
    public void handlerException(Exception e) {
        System.out.println("catch RuntimeException: " + e.getMessage());
    }
}
