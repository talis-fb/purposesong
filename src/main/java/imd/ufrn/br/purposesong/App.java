package imd.ufrn.br.purposesong;

import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    private Scene mainViewScene;
    private Scene loginViewScene;
    private Scene registerViewScene;
    private Scene menuViewScene;

    public void setLoginScene(Scene scene) {
        this.loginViewScene = scene;
    }

    public void setMainScene(Scene scene) {
        this.mainViewScene = scene;
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
        this.stage.show();
    }

    public void changeToMainScene() {
        this.currentScene = this.mainViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void changeToRegisterScene() {
        this.currentScene = this.registerViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void changeToMenuScene() {
        this.currentScene = this.menuViewScene;
        this.stage.setScene(this.currentScene);
        // this.stage.getIcons()
        // .add(new
        // Image("../../../../../resources/imd/ufrn/br/purposesong/images/headphoneLOGO.jpg"));
        this.stage.show();
    }

    public void verifyMaximized() {
        if (this.stage.isMaximized()) {

        }
    }

}
