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

                // Main Scence
                FXMLLoader fxmlLoaderMainScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/MainView.fxml"));
                Scene mainScene = new Scene(fxmlLoaderMainScene.load(), 320, 240);

                // Login Scene
                /*
                 * FXMLLoader fxmlLoaderLoginScene = new FXMLLoader(
                 * AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/LoginView.fxml"))
                 * ;
                 * Scene loginScene = new Scene(fxmlLoaderLoginScene.load(), 320, 240);
                 */
                // Register Scene
                FXMLLoader fxmlLoaderRegisterScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/Register.fxml"));
                Scene registerScene = new Scene(fxmlLoaderRegisterScene.load());

                FXMLLoader fxmlLoaderMenuScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/MenuView.fxml"));
                Scene menuScene = new Scene(fxmlLoaderMenuScene.load());

                FXMLLoader fxmlLoaderLoginScene = new FXMLLoader(
                                AppStarter.class.getResource("/imd/ufrn/br/purposesong/view/Login.fxml"));
                Scene loginScene = new Scene(fxmlLoaderLoginScene.load());

                app.setStage(stage);
                // app.setMainScene(mainScene);
                app.setLoginScene(loginScene);
                app.setRegisterScene(registerScene);
                app.setMenuScene(menuScene);
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