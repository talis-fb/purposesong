package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;

import java.util.UUID;

public class UpdateUser {
    final UserRepository userRepository;
    public UpdateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID id, User user) {
        this.userRepository.update(id, user);
    }
}
