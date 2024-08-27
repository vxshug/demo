package site.shug.spring.mvc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import site.shug.spring.mvc.model.User;

import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User("shug", "1234");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }
}
