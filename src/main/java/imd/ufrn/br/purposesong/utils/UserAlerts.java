package imd.ufrn.br.purposesong.utils;

import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserAlerts {
    public static void alertLoginMessage() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("USER NOT FOUND");
        alert.setHeaderText("Something is wrong!");
        alert.setContentText("Please, check your information and try again");
        alert.show();
    }

    public static boolean alertVerifyaddUser(User user) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("CREATE NEW USER");
        alert.getButtonTypes().set(1, ButtonType.CANCEL);
        alert.setHeaderText(
                "Are you sure you want to add " + user.getName().toUpperCase() + " to the system as a "
                        + (user.isVipUser() ? "VipUser" : "NormalUser"));
        alert.setContentText(user.getName() + " will be able to acess the system by themself if you register");
        var result = alert.showAndWait();
        return result.get().getText().equals("OK");
    }

    public static void alertEmpytUser() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("EMPYT FIELD");
        alert.setHeaderText("Some field is empty");
        alert.setContentText("You need to complete all the register fields too continue");
        alert.show();
    }

    public static void alertStartHere() {
        Alert alert = new Alert(AlertType.INFORMATION);
        // alert.setGraphic(new
        // ImageView("file:src/main/resources/imd/ufrn/br/purposesong/images/headphoneLOGO.png"));
        alert.getDialogPane().getStylesheets()
                .add("file:src/main/resources/imd/ufrn/br/purposesong/themes/userAlert.css");
        alert.getDialogPane().getStyleClass().add("userAlert");
        alert.setTitle("Welcome, you!");
        alert.setHeaderText("Welcome to PurposeSong!");
        alert.setContentText("Please follow the steps bellow before you continue: \n"
                + "1st. You need to create a new account before start using this application. \n Click on the headphone for sign up for the first time!");
        alert.setWidth(400);
        alert.setHeight(400);
        alert.show();
    }

    public static void alertPasswordsDoNotMatch() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Passwords do not match");
        alert.setContentText(
                "Please ensure that Password and Confirm password fields match exactly.  The following passwords were not correctly confirmed:");
        alert.showAndWait();
    }

    public static void alertAboutUs() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About us | Contacts");
        alert.setContentText(
                "If you have any issue, you can contact us by the links bellow:\n"
                        + "Talison: https://github.com/talis-fb\nJuliana: https://github.com/julianasantiago100");
        alert.show();
    }

    public static boolean alertUpdateConfirmation() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Updating your user account");
        alert.setContentText(
                "Please, check your information before you continue.\n Are you sure you want to update your account?");
        var result = alert.showAndWait();
        return result.get().getText().equals("OK");
    }

    public static void alertSomePasswordIsWrong() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Passwords do not match");
        alert.setContentText(
                "Please ensure that Password and Confirm password fields match exactly or/and check your old password.");
        alert.showAndWait();
    }

    public static void alertYouAlreadyAddedThisSong() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("You have this song!");
        alert.setContentText(
                "You already have this song in your playlist. Please add one that you dont have yet");
        alert.show();
    }

    public static boolean alertNewPlaylistConfirmation(Playlist playlist) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm your data");
        alert.setContentText("Do you want to add " + playlist.getName() + " playlist to your account?");
        var result = alert.showAndWait();
        return result.get().getText().equals("OK");
    }
}
