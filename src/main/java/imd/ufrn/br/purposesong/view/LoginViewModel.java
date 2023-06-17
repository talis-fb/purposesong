package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryUserRepositoryImpl;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.use_case.LoginUser;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class LoginViewModel {
    private App app = App.getInstance();
    private UserStore userStore = UserStore.getInstance();

    // Fields
    public final StringProperty username = new SimpleStringProperty("admin");
    public final StringProperty password = new SimpleStringProperty("admin");

    public boolean submitLogin() {
        String email = this.username.get();
        String password = this.password.get();

        var repo = RepositoryFactory.getUserRepository();
        Optional<User> output = new LoginUser(repo).execute(email, password);
        boolean isUserLogged = output.isPresent();

        if (isUserLogged) {
            System.out.println("[LOGIN]: Sucesso");

            // Add User Loged
            userStore.setUser(output.get());

            // !Change to Menu screen
            this.app.changeToMenuScene();
        } else {
            System.out.println("[LOGIN]: ERRO -> ususário não encontrado");
            UserAlerts.alertLoginMessage();
        }

        return isUserLogged;

    }

    // Singleton ----------
    private static final LoginViewModel instance = new LoginViewModel();

    private LoginViewModel() {
    }

    public static LoginViewModel getInstance() {
        return LoginViewModel.instance;
    }
}
