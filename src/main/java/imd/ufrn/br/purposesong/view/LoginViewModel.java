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
    private final StringProperty labelContent = new SimpleStringProperty("conteudo: ");

    public StringProperty helloMessage() {
        return labelContent;
    }

    public String getHelloMessage() {
        return labelContent.get();
    }

    public void setHelloMessage(String message) {
        labelContent.set(message);
    }

    public void goToMenu() {
        this.app.changeToMenuScene();
    }
}
