package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.UserRepository;

public class GetAllUsers {
    final UserRepository userRepository;

    public GetAllUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int execute() {
        return userRepository.quantityORegisterUsers();
    }
}
