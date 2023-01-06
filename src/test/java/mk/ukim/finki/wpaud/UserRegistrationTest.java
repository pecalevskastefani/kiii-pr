package mk.ukim.finki.wpaud;

import mk.ukim.finki.wpaud.model.enumerations.Role;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wpaud.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wpaud.model.exceptions.PasswordsDoNotMatchExceptions;
import mk.ukim.finki.wpaud.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wpaud.repository.jpa.UserRepository;
import mk.ukim.finki.wpaud.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock //nema vistinki da se vrsat kon paza na podatoci tuku ke se simuliraat
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl service;

    //pred site ostanati testovi da se izvrsi prvo init metodot
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this); //site mock anotacii da se istancirani
        User user = new User("username", "password", "name", "surename", Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user); //koga ke se povika ova togas vrati go userot gore
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");
        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
        //inicjalizacija na userservice , spy metodot go kreira so dvete zavisnosti
        //spy go vitka metodot so proxy i ni ovozmozuva da vidime dali e povikanmetodot so soodvetnite vrednosti
    }

    @Test
    public void testSuccessRegister() {
        User user = this.service.register("username", "password",
                "password", "name", "surename", Role.ROLE_USER);
        //ovoj sakame da go testirame, ocekuvame da bideme registiran

        Mockito.verify(this.service).register("username", "password",
                "password", "name", "surename", Role.ROLE_USER);
        //da vidime so verify dali e povikan metodot so soodvetnite argumenti


        Assert.assertNotNull("User is null", user);
        //ako e null ovaa proaka ke se ispecati
        Assert.assertEquals("name do not mach", "name", user.getName());
        //ke proverime deka imeto na korisnikot e imeto shto go prativme i gi proveruvam site ostanati svojstva
        Assert.assertEquals("role do not mach", Role.ROLE_USER, user.getRole());
        Assert.assertEquals("surename do not mach", "surename", user.getSurname());
        Assert.assertEquals("password do not mach", "password", user.getPassword());
        Assert.assertEquals("username do not mach", "username", user.getUsername());

    }

    //koga username e null
    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(null, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "password", "password", "name", "surename", Role.ROLE_USER);
    }

    //usernameot prazen string
    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password, "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", "name", "surename", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentsException.class,
                () -> this.service.register(username, password, "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password", "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchExceptions.class,
                () -> this.service.register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "password", "name", "surename", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", "name", "surename", Role.ROLE_USER);
    }


}
