package site.shug.spring.mvc.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.shug.spring.mvc.request.UserRequest;


import java.io.IOException;
import java.util.ArrayList;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super.setAuthenticationManager(authenticationManager);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserRequest loginRequest = objectMapper.readValue(request.getInputStream(), UserRequest.class);
            UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.unauthenticated(
                    loginRequest.name, loginRequest.password);
            return this.getAuthenticationManager().authenticate(authToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        // Handle unsuccessful authentication
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication Failed: " + failed.getMessage());
    }
}
