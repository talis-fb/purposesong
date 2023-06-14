package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryUserRepositoryImpl;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.use_case.LoginUser;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class LoginViewModel {
    private App app = App.getInstance();

    // Fields
    public final StringProperty username = new SimpleStringProperty("admin");
    public final StringProperty password = new SimpleStringProperty("admin");

    public boolean submitLogin() {
        String email = this.username.get();
        String password = this.password.get();

        var repo = InMemoryUserRepositoryImpl.getInstance();
        Optional<User> output = new LoginUser(repo).execute(email, password);
        boolean isUserLogged = output.isPresent();

        if (isUserLogged) {
            System.out.println("[LOGIN]: Sucesso");

            // Add User Loged
            UserSession.getInstance().setUser(output.get());
            System.out.println(UserSession.getInstance().getUser().getName());
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
