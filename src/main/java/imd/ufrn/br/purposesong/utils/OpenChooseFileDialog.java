package imd.ufrn.br.purposesong.utils;

import java.io.File;
import java.util.Optional;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OpenChooseFileDialog {

    public static Optional<File> showChooseFileDialog(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a music file");
        File selected = fileChooser.showOpenDialog(stage);
        stage.show();
        return Optional.ofNullable(selected);
    }

}
