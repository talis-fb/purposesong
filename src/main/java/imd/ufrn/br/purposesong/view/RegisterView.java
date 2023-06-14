package imd.ufrn.br.purposesong.view;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;

public class RegisterView implements Initializable {
    private RegisterViewModel viewModel = RegisterViewModel.getInstance();

    @FXML
    private PasswordField RepeatPasswordField;

    @FXML
    private TextField UserNameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField EmailField;

    @FXML
    private CheckBox isVip;

    @FXML
    protected void goToMenu() {
        this.viewModel.goToMenu();
        UserNameField.clear();
        EmailField.clear();
        PasswordField.clear();
        RepeatPasswordField.clear();
    }

    @FXML
    protected void createNewUser() {
        if (UserNameField.getText().isEmpty() || EmailField.getText().isEmpty() || PasswordField.getText().isEmpty()
                || RepeatPasswordField.getText().isEmpty()) {
            this.viewModel.empty();
        } else {
            if (this.viewModel.createNewUser(UserNameField.getText(), EmailField.getText(), PasswordField.getText(),
                    isVip.isSelected())) {
                UserNameField.clear();
                EmailField.clear();
                PasswordField.clear();
                RepeatPasswordField.clear();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
