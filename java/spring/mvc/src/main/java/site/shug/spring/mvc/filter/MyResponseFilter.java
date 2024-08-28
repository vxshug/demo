package site.shug.spring.mvc.filter;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import site.shug.spring.mvc.handler.MyHandlerMethodArgumentResolver;

import java.io.IOException;

@Component
public class MyResponseFilter implements Filter {
    @Autowired
    public MyHandlerMethodArgumentResolver resolver;

    @Autowired
    @Qualifier("springSecurityFilterChain")
    private Filter filterChain;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("resolver: " + resolver);
        chain.doFilter(request, response);
    }
}
