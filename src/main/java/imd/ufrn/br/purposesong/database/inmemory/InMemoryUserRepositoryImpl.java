package imd.ufrn.br.purposesong.database.inmemory;

import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.view.LoginViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserRepositoryImpl implements UserRepository {
    private static List<User> users = new ArrayList<User>();
    private static InMemoryUserRepositoryImpl instance = new InMemoryUserRepositoryImpl();
    private InMemoryUserRepositoryImpl() {
        var userAdmin = new User();
        userAdmin.setEmail("admin");
        userAdmin.setName("admin");
        userAdmin.setPassword("admin");
        userAdmin.setVipUser();
        users.add(userAdmin);
    }
    public static InMemoryUserRepositoryImpl getInstance() {
        return InMemoryUserRepositoryImpl.instance;
    }




    @Override
    public Optional<User> findById(String id) {
        for (var user : InMemoryUserRepositoryImpl.users) {
            if (user.getId().orElse("").equals(id)) {
                return Optional.ofNullable(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return InMemoryUserRepositoryImpl.users;
    }

    @Override
    public void create(User user) {
        if (user.getId().isPresent()){
            System.out.println("[ERRO]: Voce deve criar usuario nao registrados no banco de dados");
        }

        user.setId(UUID.randomUUID().toString());
        InMemoryUserRepositoryImpl.users.add(user);
    }

    @Override
    public void update(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            var user_it = users.get(i);
            if (user_it.getId().orElse("").equals(id)) {
                users.set(i, user);
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User person = iterator.next();

            if (person.getId().isEmpty()) {
                continue;
            }

            if (person.getId().get().equals(id)) {
                iterator.remove();
            }
        }
    }

    @Override
    public Optional<User> login(String email, String password) {
        for (var user_it : InMemoryUserRepositoryImpl.users) {
            var it_email = user_it.getEmail();
            var it_password = user_it.getPassword();
            if (it_email.equals(email) && it_password.equals(password)) {
                return Optional.of(user_it);
            }
        }
        return Optional.empty();
    }

}
