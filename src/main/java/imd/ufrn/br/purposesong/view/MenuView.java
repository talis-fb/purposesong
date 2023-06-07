package imd.ufrn.br.purposesong.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import imd.ufrn.br.purposesong.App;

public class MenuView implements Initializable {
    private MenuViewModel viewModel = MenuViewModel.getInstance();

    @FXML
    private ListView<String> SongView;

    @FXML
    private ImageView atual_imagem;

    @FXML
    private Label myLabel;

    @FXML
    private AnchorPane currentSong;

    @FXML
    protected void goToRegister() {
        this.viewModel.goToRegister();
    }

    @FXML
    protected void goToLogin() {
        currentSong.visibleProperty().set(false);
        this.viewModel.goToLogin();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SongView.getItems().addAll(viewModel.getMusicas());

        SongView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currentSong.visibleProperty().set(true);
                viewModel.setCurrentSong(SongView.getSelectionModel().getSelectedItem());
                try {
                    atual_imagem.setImage(viewModel.getImages().get(SongView.getSelectionModel().getSelectedIndex()));
                } catch (Exception e) {
                    atual_imagem
                            .setImage(new Image(viewModel.getDefaultImage()));
                }
                myLabel.setText(viewModel.getCurrentSong());

                ;
            }
        });
    }
}
