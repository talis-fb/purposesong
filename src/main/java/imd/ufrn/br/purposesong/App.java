package imd.ufrn.br.purposesong;

import java.util.Timer;
import java.util.TimerTask;

import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.SongStore;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App {

    private UserStore userStore = UserStore.getInstance();
    private SongStore songStore = SongStore.getInstance();

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.getIcons()
                .add(new Image("file:src/main/resources/imd/ufrn/br/purposesong/images/headphoneLOGO.png"));
    }

    // Scenes
    private Scene currentScene;
    private Scene loginViewScene;
    private Scene registerViewScene;
    private Scene menuVipViewScene;
    private Scene settingsViewScene;
    private Scene menuNormalViewScene;

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public void setSettingsScene(Scene scene) {
        this.settingsViewScene = scene;
    }

    public void setLoginScene(Scene scene) {
        this.loginViewScene = scene;
    }

    public void setRegisterScene(Scene scene) {
        this.registerViewScene = scene;
    }

    public void setMenuVipScene(Scene scene) {
        this.menuVipViewScene = scene;
    }

    public void setMenuNormalViewScene(Scene scene) {
        this.menuNormalViewScene = scene;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void changeToLoginScene() {
        this.currentScene = this.loginViewScene;
        this.userStore.resetUser();
        this.songStore.resetStore();
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void changeToRegisterScene() {
        this.currentScene = this.registerViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void changeToMenuScene() {
        this.currentScene = this.userStore.getUser().get().isVipUser()
                ? this.menuVipViewScene
                : this.menuNormalViewScene;

        this.songStore.fetchSongListOfCurrentUser();

        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void changeToSettingsScene() {
        this.currentScene = this.settingsViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void startHere() {
        this.changeToLoginScene();

        if (this.userStore.quantityOfUsers() == 0)
            UserAlerts.alertStartHere();
    }

    // Singleton ----------
    private static final App instance = new App();

    private App() {
    }

    public static App getInstance() {
        return App.instance;
    }
}
