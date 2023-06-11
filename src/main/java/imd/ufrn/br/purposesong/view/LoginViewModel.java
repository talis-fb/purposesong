package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryUserRepositoryImpl;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.use_case.LoginUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class LoginViewModel {
    private App app = App.getInstance();

    // Singleton ----------
    private static LoginViewModel instance = new LoginViewModel();
    private LoginViewModel() {}
    public static LoginViewModel getInstance() {
        return LoginViewModel.instance;
    }
    // -----------------

    // Fields
    public final StringProperty username = new SimpleStringProperty();
    public final StringProperty password = new SimpleStringProperty();


    public boolean submitLogin() {
        String email = this.username.get();
        String password = this.password.get();

        var repo = InMemoryUserRepositoryImpl.getInstance();
        Optional<User> output = (new LoginUser(repo)).execute(email, password);
        boolean isUserLogged = output.isPresent();

        if (isUserLogged) {
            System.out.println("[LOGIN]: Sucesso");
            this.app.changeToMenuScene();
        } else {
            System.out.println("[LOGIN]: ERRO -> ususário não encontrado");
        }

        return isUserLogged;

    }
}
