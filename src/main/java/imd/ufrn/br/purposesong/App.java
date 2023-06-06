package imd.ufrn.br.purposesong;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class App {
    // Singleton ----------
    private static App instance = new App();
    private App() {}
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
    private Scene mainViewScene;
    private Scene loginViewScene;

    public void setLoginScene(Scene scene) {
        this.loginViewScene = scene;
    }

    public void setMainScene(Scene scene) {
        this.mainViewScene = scene;
    }


    public void changeToLoginScene() {
        this.currentScene = this.loginViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void changeToMainScene() {
        this.currentScene = this.mainViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }


}

