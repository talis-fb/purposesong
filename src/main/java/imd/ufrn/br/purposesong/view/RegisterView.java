package imd.ufrn.br.purposesong.view;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterView implements Initializable {
    private RegisterViewModel viewModel = RegisterViewModel.getInstance();

    @FXML
    private PasswordField RepeatPasswordField;

    @FXML
    private TextField FullNameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField EmailField;

    @FXML
    protected void goToMenu() {
        this.viewModel.goToMenu();
        FullNameField.clear();
        EmailField.clear();
        PasswordField.clear();
        RepeatPasswordField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
