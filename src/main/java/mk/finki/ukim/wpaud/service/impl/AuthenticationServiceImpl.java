package mk.finki.ukim.wpaud.service.impl;

import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.wpaud.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wpaud.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wpaud.model.exceptions.UsernameExistsException;
import mk.finki.ukim.wpaud.repository.impl.InMemoryUserRepository;
import mk.finki.ukim.wpaud.repository.jpa.UserRepository;
import mk.finki.ukim.wpaud.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
        //orElseThrow(() -> new InvalidUserCredentialsException()); because it asks for a supplier
    }

    @Override
    public User register(String name, String surname, String username, String password, String repeatPassword) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        User u = new User(username, password, name, surname);
        if (userRepository.findByUsername(username).isPresent()
                || !userRepository.findByUsername(username).isEmpty()){
            throw new UsernameExistsException(username);
        }
        return userRepository.save(u);
    }

}
