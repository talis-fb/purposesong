package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    // Singleton ----------
    private static LoginViewModel instance = new LoginViewModel();

    private LoginViewModel() {
    }

    public static LoginViewModel getInstance() {
        return LoginViewModel.instance;
    }
    // -----------------

    // Fields
    private App app = App.getInstance();

    public void goToMenu() {
        this.app.changeToMenuScene();
    }

    public void authenticityLogin() {
        // !Verify login

        // !Found out which type of user it's using the application
    }
}
