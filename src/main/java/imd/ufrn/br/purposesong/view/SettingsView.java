package imd.ufrn.br.purposesong.view;

import java.net.URL;
import java.util.ResourceBundle;

import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SettingsView implements Initializable {

    private SettingsViewModel viewModel = SettingsViewModel.getInstance();
    @FXML
    private CheckBox changePassword;

    @FXML
    private Label newPassword;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Label repeatPassword;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private Label oldPassword;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField emailField;

    @FXML
    protected void enableChangePasswordFields() {
        if (changePassword.isSelected()) {
            newPassword.setDisable(false);
            oldPassword.setDisable(false);
            repeatPassword.setDisable(false);
            newPasswordField.setDisable(false);
            oldPasswordField.setDisable(false);
            repeatPasswordField.setDisable(false);
        } else {
            newPassword.setDisable(true);
            oldPassword.setDisable(true);
            repeatPassword.setDisable(true);
            newPasswordField.setDisable(true);
            oldPasswordField.setDisable(true);
            repeatPasswordField.setDisable(true);
        }
    }

    public void goToMenu() {
        this.viewModel.goToMenu();
        newPasswordField.clear();
        oldPasswordField.clear();
        repeatPasswordField.clear();
    }

    @FXML
    protected void updateUserSettings() {

        if (userNameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            this.viewModel.empty();
            return;
        } else {
            this.viewModel.updateUserNameAndEmail(userNameField.getText().toString(), emailField.getText().toString());
        }

        if (changePassword.isSelected()) {
            if (oldPasswordField.getText().isEmpty()
                    || newPasswordField.getText().isEmpty()
                    || repeatPasswordField.getText().isEmpty()) {
                this.viewModel.empty();
                return;
            } else if (this.viewModel.authenticationOldPassword(oldPasswordField.getText().toString())
                    && this.viewModel.verifyingRepeatPassword(newPasswordField.getText().toString(),
                            repeatPasswordField.getText().toString())) {
                this.viewModel.updateUserPassword(newPasswordField.getText().toString());
            } else {
                UserAlerts.alertSomePasswordIsWrong();
                return;
            }
        }
        this.viewModel.updateConfirmation();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameField.textProperty().bindBidirectional(UserStore.getInstance().activeUserLabelName);
        emailField.textProperty().bindBidirectional(UserStore.getInstance().activeUserLabelEmail);
    }
}
