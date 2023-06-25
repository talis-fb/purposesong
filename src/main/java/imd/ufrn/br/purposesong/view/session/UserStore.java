package imd.ufrn.br.purposesong.view.session;

import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.use_case.GetAllUsers;
import imd.ufrn.br.purposesong.use_case.UpdateUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class UserStore {
    private Optional<User> user;
    public StringProperty activeUserLabelName = new SimpleStringProperty("");
    public StringProperty activeUserLabelEmail = new SimpleStringProperty("");

    public Optional<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = Optional.of(user);
        this.activeUserLabelName.set(this.user.map(User::getName).orElse(" "));
        this.activeUserLabelEmail.set(this.user.map(User::getEmail).orElse(" "));
    }

    public void resetUser() {
        this.user = Optional.empty();
    }

    public int quantityOfUsers() {
        var repo = RepositoryFactory.getUserRepository();
        return new GetAllUsers(repo).execute();
    }

    public void updateUserInDB() {
        var repo = RepositoryFactory.getUserRepository();
        new UpdateUser(repo).execute(this.user.get().getId().get(), this.user.get());
    }

    // Singleton
    private static final UserStore instance = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return UserStore.instance;
    }
}
