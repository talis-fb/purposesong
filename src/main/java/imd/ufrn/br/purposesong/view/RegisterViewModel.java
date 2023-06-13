package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import javafx.beans.property.StringProperty;

public class RegisterViewModel {
    private App app = App.getInstance();

    public void goToMenu() {
        this.app.changeToMenuScene();
    }

    // Singleton -------------------
    private static final RegisterViewModel instance = new RegisterViewModel();

    private RegisterViewModel() {
    }

    public static RegisterViewModel getInstance() {
        return RegisterViewModel.instance;
    }
}
