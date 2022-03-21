package mk.finki.ukim.wpaud.web.controller;

import mk.finki.ukim.wpaud.model.enumerations.Role;
import mk.finki.ukim.wpaud.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.wpaud.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wpaud.service.AuthenticationService;
import mk.finki.ukim.wpaud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public RegisterController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasErrors", error);
            model.addAttribute("error", error);

        }

        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role)
    {
        try {
            userService.register(username, password, repeatPassword, name, surname,role);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidArgumentException e) {
            return "redirect:/register?error=" + e.getMessage();
        }

    }
}
