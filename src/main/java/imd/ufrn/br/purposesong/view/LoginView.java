package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LoginView implements Initializable {
    private LoginViewModel viewModel = LoginViewModel.getInstance();
    private UserStore userStore = UserStore.getInstance();

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TextField userNameInput;

    @FXML
    private PasswordField passwordInput;
    @FXML
    private ImageView signAddNewUser;

    @FXML
    private ImageView bigLogoImage;

    @FXML
    protected void submitLogin() {
        this.viewModel.submitLogin();
    }

    @FXML
    protected void signUpFirstUser() {
        this.viewModel.signUpFirstUser();
    }

    @FXML
    protected void showNewUserProperty() {
        // !Seta a funcionalidade de add novo usuário ao sistema
        signAddNewUser.setVisible(true);
    }

    @FXML
    protected void hideNewUserProperty() {
        // !Seta a funcionalidade de add novo usuário ao sistema
        signAddNewUser.setVisible(false);
    }

    @FXML
    protected void updateFirstUserField() {
        // !Não permite mais cadastros foram do sistema.
        // !Funcionalidade não aplica ainda.
        System.out.println("Usuários cadastrados: " + this.userStore.quantityOfUsers());
        boolean userRegistered = this.userStore.quantityOfUsers() != 0;

        if (userRegistered) {
            signAddNewUser.setVisible(false);
            bigLogoImage.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameInput.textProperty().bindBidirectional(this.viewModel.username);
        passwordInput.textProperty().bindBidirectional(this.viewModel.password);

    }
}