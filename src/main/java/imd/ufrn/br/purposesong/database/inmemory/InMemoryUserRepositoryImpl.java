package imd.ufrn.br.purposesong.database.inmemory;

import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepositoryImpl extends InMemoryAbstractRepository<User> implements UserRepository {
    private static final List<User> users = new ArrayList<>();
    @Override
    public List<User> findAll() {
        return InMemoryUserRepositoryImpl.users;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.findAll()
                .stream()
                .filter(it -> it.getEmail().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> login(String email, String password) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
    }



    // Singleton ---------------------
    private static final InMemoryUserRepositoryImpl instance = new InMemoryUserRepositoryImpl();
    private InMemoryUserRepositoryImpl() {
        var userAdmin = new User();
        userAdmin.setEmail("admin");
        userAdmin.setName("admin");
        userAdmin.setPassword("admin");
        userAdmin.setVipUser();

        this.create(userAdmin);
    }
    public static InMemoryUserRepositoryImpl getInstance() {
        return InMemoryUserRepositoryImpl.instance;
    }
}
