package mk.finki.ukim.wpaud.web.controller;

import mk.finki.ukim.wpaud.service.AuthenticationService;
import org.springframework.stereotype.Controller;
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
    public String getRegregister(){
        return "register";
    }

    @PostMapping
    public String register (@RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String repeatPassword){
        authenticationService.register(name, surname, username, password, repeatPassword);
        return "redirect:/login";
    }
}
