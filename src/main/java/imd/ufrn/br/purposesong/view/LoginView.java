package imd.ufrn.br.purposesong.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

public class LoginView implements Initializable {
    private LoginViewModel viewModel = LoginViewModel.getInstance();

    /*
     * @FXML
     * private Label labelContent;
     * 
     * @FXML
     * protected void onButtonClick() {
     * var content = this.viewModel.getHelloMessage();
     * this.viewModel.setHelloMessage(content + " +");
     * }
     */

    @FXML
    private TextField userNameInput;

    @FXML
    private PasswordField PasswordInput;

    /*
     * public TextField getUserNameInput() {
     * return userNameInput;
     * }
     * 
     * @FXML
     * public void setUserNameInput(InputMethodEvent event) {
     * // System.out.println(userNameInput.getText());
     * }
     * 
     * public PasswordField getPasswordInput() {
     * return PasswordInput;
     * }
     * 
     * @FXML
     * public void setPasswordInput(InputMethodEvent event) {
     * // PasswordInput = passwordInput;
     * }
     */
    @FXML
    protected void goToMenu() {
        this.viewModel.goToMenu();
        userNameInput.clear();
        PasswordInput.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // labelContent.textProperty().bind(this.viewModel.helloMessage());
    }
}