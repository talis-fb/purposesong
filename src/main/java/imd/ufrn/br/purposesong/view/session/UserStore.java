package imd.ufrn.br.purposesong.view.session;

import imd.ufrn.br.purposesong.entity.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserStore {
    private User user;
    public StringProperty activeUserLabelName = new SimpleStringProperty("");

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.activeUserLabelName.set(this.user.getName());
    }

    // Singleton
    private static final UserStore instance = new UserStore();
    private UserStore() {}
    public static UserStore getInstance() {
        return UserStore.instance;
    }
}
