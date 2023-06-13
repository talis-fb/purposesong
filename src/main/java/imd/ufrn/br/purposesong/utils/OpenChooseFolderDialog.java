package imd.ufrn.br.purposesong.utils;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class OpenChooseFolderDialog {

    public static File showChooseFolderDialog(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a music folder");
        File selectedDirectory = directoryChooser.showDialog(stage);
        stage.show();
        return selectedDirectory;
    }

}
