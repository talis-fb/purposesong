package imd.ufrn.br.purposesong.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryUserRepositoryImpl;
import imd.ufrn.br.purposesong.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoginUserTest {
    private UserRepository userRepository;
    private LoginUser loginUser;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepositoryImpl();
        loginUser = new LoginUser(userRepository);
    }

    @Test
    void testExecute_LoginSuccessful() {
        String email = "test@example.com";
        String password = "password123";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.create(user);

        Optional<User> result = loginUser.execute(email, password);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void testExecute_LoginFailed() {
        String email = "test@example.com";
        String password = "password123";

        Optional<User> result = loginUser.execute(email, password);

        assertFalse(result.isPresent());
    }
}