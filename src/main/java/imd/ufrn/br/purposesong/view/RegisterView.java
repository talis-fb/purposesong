package imd.ufrn.br.purposesong.view;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterView implements Initializable {
    private RegisterViewModel viewModel = RegisterViewModel.getInstance();

    @FXML
    protected void goToMenu() {
        this.viewModel.goToMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
