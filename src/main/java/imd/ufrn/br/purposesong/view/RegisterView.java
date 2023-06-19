package imd.ufrn.br.purposesong.view;

import java.util.ResourceBundle;

import imd.ufrn.br.purposesong.utils.UserAlerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.PopupWindow.AnchorLocation;

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
    private Button backButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    protected void back() {
        this.viewModel.back();
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
            return;
        }

        if (!this.viewModel.verifyingRepeatPassword(PasswordField.getText().toString(),
                RepeatPasswordField.getText().toString())) {
            UserAlerts.alertPasswordsDoNotMatch();
            return;
        }

        if (this.viewModel.createNewUser(UserNameField.getText(), EmailField.getText(), PasswordField.getText(),
                isVip.isSelected())) {
            UserNameField.clear();
            EmailField.clear();
            PasswordField.clear();
            RepeatPasswordField.clear();
        }

    }

    @FXML
    protected void showPasswordField() {
        var password = PasswordField.getText();
        PasswordField.clear();
        PasswordField.setPromptText(password);
    }

    @FXML
    public void hidePasswordField() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
