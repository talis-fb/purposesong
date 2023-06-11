package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.User;
import java.util.Optional;

public interface UserRepository extends AbstractRepository<User> {
    Optional<User> findUserByUsername(String username);
    Optional<User> login(String email, String password);
}
