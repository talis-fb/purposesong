package imd.ufrn.br.purposesong.database;

import imd.ufrn.br.purposesong.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);
    List<User> findAll();
    void create(User user);
    void update(String id, User user);
    void delete(String id);

    Optional<User> login(String email, String password);
}
