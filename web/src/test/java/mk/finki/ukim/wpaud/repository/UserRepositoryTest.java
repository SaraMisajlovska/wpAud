package mk.finki.ukim.wpaud.repository;

import mk.finki.ukim.wpaud.model.User;
import mk.finki.ukim.wpaud.model.enumerations.Role;
import mk.finki.ukim.wpaud.model.exceptions.UserNotFoundException;
import mk.finki.ukim.wpaud.model.projections.UserProjection;
import mk.finki.ukim.wpaud.repository.jpa.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> userList = userRepository.findAll();
        Assert.assertEquals(1L, userList.size());
    }

    @Test
    public void testFetchAll() {
        List<User> userList = userRepository.fetchAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testLoadAll() {
        List<User> userList = userRepository.loadAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testProjectUsernameAndNameAndSurname() {
        UserProjection userProjection = userRepository.findByRole(Role.ROLE_USER);
        Assert.assertEquals("sara.m", userProjection.getUsername());
        Assert.assertEquals("Sara ", userProjection.getName());
        Assert.assertEquals("Misajlovska", userProjection.getSurname());
    }

    @Test
    public void testOptimisticLock() {
        User user1 = this.userRepository.findByUsername("sara.m")
                .orElseThrow(() -> new UserNotFoundException("sara.m"));

        User user2 = this.userRepository.findByUsername("sara.m")
                .orElseThrow(() -> new UserNotFoundException("sara.m"));

        user1.setName("sara.m1");
        user1.setName("sara.m2");
        this.userRepository.save(user1);
        this.userRepository.save(user2);
    }

}
