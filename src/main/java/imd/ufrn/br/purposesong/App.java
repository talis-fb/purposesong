package imd.ufrn.br.purposesong;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App {
    // Singleton ----------
    private static App instance = new App();

    private App() {
    }

    public static App getInstance() {
        return App.instance;
    }
    // -----------------

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Scenes
    private Scene currentScene;
    private Scene loginViewScene;
    private Scene registerViewScene;
    private Scene menuViewScene;
    private Scene settingsViewScene;

    public void setSettingsScene(Scene scene) {
        this.settingsViewScene = scene;
    }

    public void setLoginScene(Scene scene) {
        this.loginViewScene = scene;
    }

    public void setRegisterScene(Scene scene) {
        this.registerViewScene = scene;
    }

    public void setMenuScene(Scene scene) {
        this.menuViewScene = scene;
    }

    public void changeToLoginScene() {
        this.currentScene = this.loginViewScene;
        this.stage.setScene(this.currentScene);
        changeSize();
        this.stage.show();
    }

    public void changeToRegisterScene() {
        this.currentScene = this.registerViewScene;
        this.stage.setScene(this.currentScene);
        changeSize();
        this.stage.show();
    }

    public void changeToMenuScene() {
        this.currentScene = this.menuViewScene;
        this.stage.setScene(this.currentScene);
        // this.stage.getIcons()
        // .add(new
        // Image("../../../../../resources/imd/ufrn/br/purposesong/images/headphoneLOGO.jpg"));
        changeSize();
        this.stage.show();
    }

    public void changeToSettingsScene() {
        this.currentScene = this.settingsViewScene;
        this.stage.setScene(this.currentScene);
        changeSize();
        this.stage.show();
    }

    public void changeToFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a music file");
        File selectedFile = fileChooser.showOpenDialog(stage);
        this.stage.show();
        System.out.println(selectedFile); // !Enviar isso para a lista de músicas!!!!
    }

    public void changeToFolderChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        this.stage.show();
        System.out.println(selectedDirectory);
    }

    public void changeSize() {
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> Observablevalue, Number number,
                    Number number2) {
                stage.setWidth((double) number2);
            }

        });

        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number,
                    Number number2) {
                stage.setHeight((double) number2);
            }
        });
        // !Trying to fix the problem
        final boolean resizable = stage.isResizable();
        stage.setResizable(!resizable);
        stage.setResizable(resizable);
    }
}
