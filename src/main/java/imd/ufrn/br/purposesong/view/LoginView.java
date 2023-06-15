package imd.ufrn.br.purposesong.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginView implements Initializable {
    private LoginViewModel viewModel = LoginViewModel.getInstance();

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TextField userNameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    protected void submitLogin() {
        this.viewModel.submitLogin();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameInput.textProperty().bindBidirectional(this.viewModel.username);
        passwordInput.textProperty().bindBidirectional(this.viewModel.password);
    }
}