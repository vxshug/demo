package site.shug.spring.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import site.shug.spring.mvc.request.UserRequest;

import java.util.Locale;

/**
 * 默认Spring MVC是不json的
 */

@RestController("/")
public class UserController {
    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    @Qualifier("i18n")
    MessageSource messageSource;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public UserRequest login(@RequestBody UserRequest user) {
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(user.name, user.password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return user;
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
