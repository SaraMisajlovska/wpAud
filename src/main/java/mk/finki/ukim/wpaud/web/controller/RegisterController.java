package mk.finki.ukim.wpaud.web.controller;

import mk.finki.ukim.wpaud.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.wpaud.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wpaud.service.AuthenticationService;
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

    public RegisterController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasErrors", error);
            model.addAttribute("error", error);

        }
        return "register";

//        model.addAttribute("bodyContent","register");
//        return "master-template";
    }

    @PostMapping
    public String register (@RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String repeatPassword){
        try {
            authenticationService.register(name, surname, username, password, repeatPassword);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidArgumentException e) {
            return "redirect:/register?error=" + e.getMessage();
        }

    }
}
