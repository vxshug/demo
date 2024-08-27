package site.shug.spring.javaweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Filter 拦截请求和修改请求
 */
@WebFilter("/user")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("UserFilter HostName: " + servletRequest.getRemoteHost());
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userName = request.getHeader("user");
        if (Objects.equals(userName, "shug")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("<html>");
            writer.write("<head>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("无效用户");
            writer.write("</body>");
            writer.write("</html>");
        }
    }
}
