package imd.ufrn.br.purposesong.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {
    private MainViewModel viewModel = MainViewModel.getInstance();

    @FXML
    private Label labelContent;

    @FXML
    protected void onButtonClick() {
        var content = this.viewModel.getHelloMessage();
        this.viewModel.setHelloMessage(content + " #");
    }

    @FXML
    protected void goToLogin() {
        this.viewModel.goToLogin();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelContent.textProperty().bind(this.viewModel.helloMessage());
        this.viewModel.helloMessage().addListener(n -> System.out.println("opaaa"));
    }
}
