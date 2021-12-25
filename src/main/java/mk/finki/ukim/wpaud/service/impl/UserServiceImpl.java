package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.enumerations.Role;
import mk.finki.ukim.wpaud.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.wpaud.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wpaud.model.exceptions.UsernameExistsException;
import mk.finki.ukim.wpaud.repository.jpa.UserRepository;
import mk.finki.ukim.wpaud.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
