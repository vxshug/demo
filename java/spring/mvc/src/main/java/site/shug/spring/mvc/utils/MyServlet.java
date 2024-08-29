package site.shug.spring.mvc.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class MyServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("super.init();");
    }
}
