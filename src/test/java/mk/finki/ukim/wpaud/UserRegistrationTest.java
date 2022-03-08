package mk.finki.ukim.wpaud;

import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.enumerations.Role;
import mk.finki.ukim.wpaud.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.wpaud.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wpaud.model.exceptions.UsernameExistsException;
import mk.finki.ukim.wpaud.repository.jpa.UserRepository;
import mk.finki.ukim.wpaud.service.UserService;
import mk.finki.ukim.wpaud.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService service;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister(){
        User user = this.service.register("username", "password","password", "name", "surname", Role.ROLE_USER);
        Mockito.verify(this.service).register("username", "password", "password", "name", "surname", Role.ROLE_USER);

        Assert.assertNotNull("User is null",user);

        Assert.assertEquals("username does not match", "username", user.getUsername());
        Assert.assertEquals("password does not match", "password", user.getPassword());
        Assert.assertEquals("name does not match", "name", user.getName());
        Assert.assertEquals("surname does not match", "surname", user.getSurname());
        Assert.assertEquals("role does not match", Role.ROLE_USER, user.getRole());
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(null, "password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "password", "password", "name", "surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, "password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surname", Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password, "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", "name", "surname", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password, "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", "name", "surname", Role.ROLE_USER);
    }

    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, password, confirmPassword, "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, confirmPassword, "name", "surname", Role.ROLE_USER);
    }

    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameExistsException.class,
                () -> this.service.register(username, "password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surname", Role.ROLE_USER);
    }

}