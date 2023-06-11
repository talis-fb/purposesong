package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;

import java.util.Optional;

public class LoginUser {
    final UserRepository userRepository;
    public LoginUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> execute(String email, String password) {
        return this.userRepository.login(email, password);
    }

}
