package imd.ufrn.br.purposesong;

import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.PlaylistStore;
import imd.ufrn.br.purposesong.view.session.SongStore;
import imd.ufrn.br.purposesong.view.session.UserStore;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App {

    private UserStore userStore = UserStore.getInstance();
    private SongStore songStore = SongStore.getInstance();
    private PlaylistStore playlistStore = PlaylistStore.getInstance();

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
        this.playlistStore.resetStore();

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

        if (this.userStore.getUser().get().isVipUser())
            this.playlistStore.fetchPlaylistListOfCurrentUser();

        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void changeToSettingsScene() {
        this.currentScene = this.settingsViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void startHere() {
        this.currentScene = this.loginViewScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();

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
