package imd.ufrn.br.purposesong.utils;

import imd.ufrn.br.purposesong.entity.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

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
}
