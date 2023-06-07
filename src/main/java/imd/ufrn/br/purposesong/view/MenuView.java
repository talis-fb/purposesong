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
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuView implements Initializable {
    private MenuViewModel viewModel = MenuViewModel.getInstance();

    // @FXML
    // private ListView<ImageView> SongView;
    @FXML
    private ListView<String> SongView;

    // @FXML
    // private ListView<Pair<ImageView, String>> SongView;

    @FXML
    private ImageView atual_imagem;

    @FXML
    private Label myLabel;

    // String[] food = { "pizza", "sushi", "ramen", "minha alma", "Grandioso és Tu",
    // "Esperança", "Só Tu és Poderoso" };
    String song_name = "Esperança";
    String currentfood;
    Image imagem = new Image(
            "file:src/main/resources/imd/ufrn/br/purposesong/images/headphoneLOGO.png");
    Image imagem2 = new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/retroceder.png");
    ImageView my_view = new ImageView(imagem);
    ImageView[] food = { my_view };
    String[] musicas = { "Sua Graça me Basta", "Apenas ti", "Me leva pra casa", "Only you God", "Esperança" };

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

        // my_view.setFitHeight(101);
        // my_view.setFitWidth(80);
        SongView.getItems().addAll(musicas);
        ArrayList<Image> images = new ArrayList<>();
        images.add(imagem);
        images.add(imagem2);

        // SongView.getItems().add(0, new Pair<ImageView, String>(my_view, song_name));
        // SongView.getItems().get(0).getKey().setImage(imagem);
        // SongView.getItems().sorted();

        SongView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currentfood = SongView.getSelectionModel().getSelectedItem();
                try {
                    atual_imagem.setImage(images.get(SongView.getSelectionModel().getSelectedIndex()));
                } catch (Exception e) {
                    atual_imagem
                            .setImage(new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/default.png"));
                }
                // myLabel.setImage(currentfood);
                myLabel.setText(currentfood);
            }
        });

        /*
         * SongView.getSelectionModel().selectedItemProperty().addListener(new
         * ChangeListener<Pair<ImageView, String>>() {
         * 
         * @Override
         * public void changed(ObservableValue<? extends Pair<ImageView, String>> arg0,
         * Pair<ImageView, String> arg1,
         * Pair<ImageView, String> arg2) {
         * // currentfood = SongView.getSelectionModel().getSelectedItem();
         * // myLabel.setImage(currentfood);
         * /// myLabel = currentfood;
         * }
         * });
         */
    }
}
