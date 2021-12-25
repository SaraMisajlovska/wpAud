package mk.finki.ukim.wpaud.web.servlet;

import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wpaud.service.AuthenticationService;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/servlet/login")

public class LoginServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthenticationService authenticationService;

    public LoginServlet(SpringTemplateEngine springTemplateEngine, AuthenticationService authenticationService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        User user = null;
        try {
            user = authenticationService.login(userName, password);
        } catch (InvalidUserCredentialsException i) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("hasErrors", true);
            context.setVariable("error", i.getMessage());
            springTemplateEngine.process("login.html", context, resp.getWriter());
        }
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/servlet/thymeleaf/category");


    }
}
