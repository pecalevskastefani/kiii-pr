package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.enumerations.Role;
import mk.ukim.finki.wpaud.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wpaud.model.projections.UserProjection;
import mk.ukim.finki.wpaud.repository.jpa.UserRepository;
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
        List<User> userList = this.userRepository.findAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testFetchAll() {
        List<User> userList = this.userRepository.fetchAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testLoadAll() {
        List<User> userList = this.userRepository.loadAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testProjectUsernameAndNameAndSurname() {
        UserProjection userProjection = this.userRepository.findByRole(Role.ROLE_USER);
        Assert.assertEquals("stefani", userProjection.getUsername());
        Assert.assertEquals("stefani", userProjection.getName());
        Assert.assertEquals("stefani", userProjection.getSurname());
    }

    //test kade konkuretno ke pristapuvame do user vo bazata
    @Test
    public void testOptimisticLock() {
        // i dvajcata pristapuvaat do istiot zapis vo bazata na podatoci
        User user1 = this.userRepository.findByUsername("stefani").orElseThrow(() -> new UserNotFoundException("stefani"));
        User user2 = this.userRepository.findByUsername("stefani").orElseThrow(() -> new UserNotFoundException("stefani"));
        //pravat promena vo istiot zapis
        user1.setName("user1");
        user2.setName("user2");
        this.userRepository.save(user1);
        this.userRepository.save(user2);


    }
}
