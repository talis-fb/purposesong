package imd.ufrn.br.purposesong.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginView implements Initializable {
    private LoginViewModel viewModel = LoginViewModel.getInstance();

    @FXML
    private TextField userNameInput;

    @FXML
    private PasswordField PasswordInput;

    @FXML
    protected void submitLogin() {
        boolean isAuth = this.viewModel.authenticityLogin();
        if (!isAuth) {
            System.out.println("aqui vc pode executar a lógica de negocio que exibe o erro para o usuario");
            // Mas se for possivel fazer no ViewModel é ate melhor
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameInput.textProperty().bindBidirectional(this.viewModel.username);
        PasswordInput.textProperty().bindBidirectional(this.viewModel.password);
    }
}