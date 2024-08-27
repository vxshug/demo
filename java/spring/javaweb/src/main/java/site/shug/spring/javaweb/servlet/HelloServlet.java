package site.shug.spring.javaweb.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 所有Servlet都继承HttpServlet
 * urlPatterns表示访问路径
 */
@WebServlet(urlPatterns = "/")
public class HelloServlet extends HttpServlet {
    /**
     *
     * @param req HTTP 请求
     * @param resp HTTP 返回
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<html><body>");
        writer.write("<h1>Hello World!</h1>");
        writer.write("</body></html>");
        writer.flush();
        writer.close();
    }
}
