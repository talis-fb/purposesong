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
    public Optional<User> findById(UUID id) {
        for (User user : InMemoryUserRepositoryImpl.users) {
            Optional<UUID> user_id = user.getId();
            if (user_id.isEmpty()) {
                continue;
            }

            if (user_id.get().equals(id)) {
                return Optional.of(user);
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
         if (user.getId().isEmpty()){
            System.out.println("[ERRO]: Voce deve criar usuario nao registrados no banco de dados");
        }

        user.setId(UUID.randomUUID());
        InMemoryUserRepositoryImpl.users.add(user);
    }

    @Override
    public void update(UUID id, User user) {
        for (int i = 0; i < users.size(); i++) {
            Optional<UUID> user_id = users.get(i).getId();
            if (user_id.isPresent() && user_id.get().equals(id)) {
                users.set(i, user);
                break;
            }
        }
    }

    @Override
    public void delete(UUID id) {
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
