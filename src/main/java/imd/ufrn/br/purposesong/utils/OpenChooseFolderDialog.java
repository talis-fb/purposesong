package imd.ufrn.br.purposesong.utils;

import java.io.File;
import java.util.Optional;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class OpenChooseFolderDialog {

    public static Optional<File> showChooseFolderDialog(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a music folder");
        File selectedDirectory = directoryChooser.showDialog(stage);
        stage.show();
        return Optional.ofNullable(selectedDirectory);
    }

}
