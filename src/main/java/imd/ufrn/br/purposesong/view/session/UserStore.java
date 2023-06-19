package imd.ufrn.br.purposesong.view.session;

import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.use_case.GetAllUsers;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class UserStore {
    private Optional<User> user;
    public StringProperty activeUserLabelName = new SimpleStringProperty("");

    public Optional<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = Optional.of(user);
        this.activeUserLabelName.set("Olá, " + this.user.map(User::getName).orElse(" "));
    }

    public void resetUser() {
        this.user = Optional.empty();
    }

    public int quantityOfUsers() {
        var repo = RepositoryFactory.getUserRepository();
        return new GetAllUsers(repo).execute();
    }

    // Singleton
    private static final UserStore instance = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return UserStore.instance;
    }
}
