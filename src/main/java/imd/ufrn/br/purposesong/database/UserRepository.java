package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    List<User> findAll();
    void create(User user);
    void update(UUID id, User user);
    void delete(UUID id);

    Optional<User> login(String email, String password);
}
