package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import javafx.beans.property.StringProperty;

public class RegisterViewModel {
    // Singleton
    private static RegisterViewModel instance = new RegisterViewModel();

    private RegisterViewModel() {
    }

    public static RegisterViewModel getInstance() {
        return RegisterViewModel.instance;
    }

    // Fields
    private App app = App.getInstance();

    public void goToMenu() {
        this.app.changeToMenuScene();
    }
}
