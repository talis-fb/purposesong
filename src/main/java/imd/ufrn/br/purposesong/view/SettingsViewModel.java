package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.UserStore;

public class SettingsViewModel {
    private App app = App.getInstance();
    private UserStore user = UserStore.getInstance();

    public void updateUserNameEmail(String name, String email) {
        user.getUser().get().setName(name);
        user.getUser().get().setEmail(email);
        UserAlerts.alertUpdateConfirmation();
        this.goToMenu();
    }

    public void updateAllUserSettings(String name, String email, String oldPass, String newPass, String repeatNewPass,
            boolean changePassword) {
        user.getUser().get().setName(name);
        user.getUser().get().setEmail(email);

        // !Veryfing passwords
        if (changePassword) {
            if (this.authenticationOldPassword(oldPass) && this.verifyingRepeatPassword(newPass, repeatNewPass)) {
                user.getUser().get().setPassword(newPass);
                if (UserAlerts.alertUpdateConfirmation()) {
                    this.goToMenu();
                }
            } else {
                UserAlerts.alertSomePasswordIsWrong();
                return;
            }

        }
    }

    public void empty() {
        UserAlerts.alertEmpytUser();
    }

    public boolean authenticationOldPassword(String oldPassword) {
        return oldPassword.equals(user.getUser().get().getPassword());
    }

    public boolean verifyingRepeatPassword(String password1, String password2) {
        System.out.println(password1 + " " + password2);
        return password1.equals(password2);
    }

    public void goToMenu() {
        this.app.changeToMenuScene();
    }

    // Singleton -----------------------
    private static final SettingsViewModel instance = new SettingsViewModel();

    private SettingsViewModel() {
    }

    public static SettingsViewModel getInstance() {
        return SettingsViewModel.instance;
    }

}
