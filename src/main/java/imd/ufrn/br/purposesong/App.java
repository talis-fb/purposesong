package imd.ufrn.br.purposesong;

import imd.ufrn.br.purposesong.view.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App {
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
        this.stage.setScene(this.currentScene);
        // changeSize();
        this.stage.show();
    }

    public void changeToRegisterScene() {
        this.currentScene = this.registerViewScene;
        this.stage.setScene(this.currentScene);
        // changeSize();
        this.stage.show();
    }

    public void changeToMenuScene() {
        if (UserSession.getInstance().getUser().isVipUser()) {
            this.currentScene = this.menuVipViewScene;
        } else {
            this.currentScene = this.menuNormalViewScene;
        }
        this.stage.setScene(this.currentScene);
        // changeSize();
        this.stage.show();
    }

    public void changeToSettingsScene() {
        this.currentScene = this.settingsViewScene;
        this.stage.setScene(this.currentScene);
        // changeSize();
        this.stage.show();
    }

    public void alertLoginMessage() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("USER NOT FOUND");
        alert.setHeaderText("Something is wrong!");
        alert.setContentText("Please, check your information and try again");
        alert.show();
    }

    public void changeSize() {
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> Observablevalue, Number number, Number number2) {
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

    // Singleton ----------
    private static final App instance = new App();

    private App() {
    }

    public static App getInstance() {
        return App.instance;
    }
}
