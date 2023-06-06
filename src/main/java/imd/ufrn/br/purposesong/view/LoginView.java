package imd.ufrn.br.purposesong.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements Initializable {
    private LoginViewModel viewModel = LoginViewModel.getInstance();


    @FXML
    private Label labelContent;

    @FXML
    protected void onButtonClick() {
        var content = this.viewModel.getHelloMessage();
        this.viewModel.setHelloMessage(content + " +");
    }

    @FXML
    protected void goToMain() {
        this.viewModel.goToMain();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelContent.textProperty().bind(this.viewModel.helloMessage());
    }
}