package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.entity.User;

public class UserSession {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Singleton
    private static final UserSession instance = new UserSession();

    private UserSession() {

    }

    public static UserSession getInstance() {
        return UserSession.instance;
    }
}
