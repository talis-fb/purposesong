package imd.ufrn.br.purposesong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppStarter extends Application {
        @Override
        public void start(Stage stage) throws IOException {
                var app = App.getInstance();

                FXMLLoader fxmlLoaderLoginScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/Login.fxml"));
                Scene loginScene = new Scene(fxmlLoaderLoginScene.load());

                FXMLLoader fxmlLoaderMenuVIPScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/MenuViewVIP.fxml"));
                Scene menuVIPScene = new Scene(fxmlLoaderMenuVIPScene.load());

                FXMLLoader fxmlLoaderMenuNormalScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/MenuViewNormal.fxml"));
                Scene menuNormalScene = new Scene(fxmlLoaderMenuNormalScene.load());

                FXMLLoader fxmlLoaderRegisterScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/Register.fxml"));
                Scene registerScene = new Scene(fxmlLoaderRegisterScene.load());

                FXMLLoader fxmlLoaderSettingsScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/Settings.fxml"));
                Scene settingsScene = new Scene(fxmlLoaderSettingsScene.load());

                app.setStage(stage);
                app.setLoginScene(loginScene);
                app.setRegisterScene(registerScene);
                app.setMenuVipScene(menuVIPScene);
                app.setSettingsScene(settingsScene);
                app.setMenuNormalViewScene(menuNormalScene);
                // ----------------
                // Initial scene
                // ----------------
                // app.changeToRegisterScene();
                app.changeToLoginScene();

        }

        public static void main(String[] args) {
                launch();
        }
}