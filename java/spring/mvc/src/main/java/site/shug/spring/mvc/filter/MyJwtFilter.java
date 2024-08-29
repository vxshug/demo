package site.shug.spring.mvc.filter;

import com.nimbusds.jose.proc.BadJOSEException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.filter.OncePerRequestFilter;
import site.shug.spring.mvc.utils.JwtUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyJwtFilter extends OncePerRequestFilter {
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticate = request.getHeader("Authorization");
        if (authenticate != null) {
            if (authenticate.startsWith("Bearer ")) {
                String token = authenticate.split("Bearer ")[1];
                if (token != null) {
                    try {
                        Jwt jwt = JwtUtils.decodeJwt(token);
                        Map<String, Object> claims = jwt.getClaims();
                        Object name = claims.get("name");
                        String ah = (String)claims.get("ah");
                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        for (String a : ah.split(";")) {
                            authorities.add(new SimpleGrantedAuthority(a));
                            authorities.add(new SimpleGrantedAuthority(a));
                        }
                        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(
                                name, "", authorities);
                        SecurityContext context = securityContextHolderStrategy.getContext();
                        context.setAuthentication(authenticated);
                    } catch (Exception e) {
                        System.out.println("JWT错误: " + e.getMessage());
                    }

                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
