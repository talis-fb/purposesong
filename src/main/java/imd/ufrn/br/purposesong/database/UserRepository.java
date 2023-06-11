package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends AbstractRepository<User> {
    Optional<User> login(String email, String password);
}
