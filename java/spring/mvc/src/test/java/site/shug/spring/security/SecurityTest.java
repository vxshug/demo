package site.shug.spring.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class SecurityTest {
    @Test
    public void testContext() {
        // 创建一个空的SecurityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        // 创建一个新的Authentication对象
        Authentication authentication =
                new TestingAuthenticationToken("username", "password", "ROLE_USER");
        // 将Authentication保存到SecurityContext
        context.setAuthentication(authentication);
        // 将SecurityContext保存到SecurityContextHolder
        SecurityContextHolder.setContext(context);

        // 获取SecurityContext
        SecurityContext context1 = SecurityContextHolder.getContext();
        // 获取Authentication
        Authentication authentication1 = context1.getAuthentication();
        String username = authentication1.getName();
        // credentials 通常是密码。在许多情况下，在用户通过身份验证后会清除该信息，以确保其不被泄露。
        Object credentials = authentication1.getCredentials();
        // principal 标识用户。当使用用户名/密码进行身份验证时，这通常是UserDetails的实例。
        Object principal = authentication1.getPrincipal();
        // authorities GrantedAuthority实例是授予用户的高级权限
        Collection<? extends GrantedAuthority> authorities = authentication1.getAuthorities();
        System.out.println(username);
        System.out.println(credentials);
        System.out.println(principal);
        System.out.println(authorities);
    }
}
