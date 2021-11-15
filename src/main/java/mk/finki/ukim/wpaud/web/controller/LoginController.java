package mk.finki.ukim.wpaud.web.controller;

import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wpaud.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//obicen kontroler vrakja view
//rest controller vrakja restful api

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String getLoginPage(Model model){

        return "login";
//        model.addAttribute("bodyContent", "login");
//        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){

        User user = null;

        try{
            user = authenticationService.login(request.getParameter("username"), request.getParameter("password"));

            request.getSession().setAttribute("user",user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException e){
           model.addAttribute("hasError", true);
           model.addAttribute("error", e.getMessage());
           return "login";
        }

    }
}
