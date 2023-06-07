package imd.ufrn.br.purposesong.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuView implements Initializable {
    private MenuViewModel viewModel = MenuViewModel.getInstance();

    @FXML
    protected void goToRegister() {
        this.viewModel.goToRegister();
    }

    @FXML
    protected void goToLogin() {
        this.viewModel.goToLogin();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
