package mk.finki.ukim.wpaud.service;


import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}