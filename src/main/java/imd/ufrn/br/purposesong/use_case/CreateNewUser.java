package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;

public class CreateNewUser {
    final UserRepository userRepository;

    public CreateNewUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {
        boolean userExist = this.userRepository.findUserByUsername(user.getEmail()).isPresent();
        if (!userExist) {
            this.userRepository.create(user);
        }
    }
}
