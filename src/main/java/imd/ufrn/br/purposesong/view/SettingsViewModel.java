package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.entity.User;
import imd.ufrn.br.purposesong.use_case.UpdateUser;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import imd.ufrn.br.purposesong.view.session.UserStore;

public class SettingsViewModel {
    private App app = App.getInstance();
    private UserStore user = UserStore.getInstance();

    public void updateUserNameAndEmail(String name, String email) {
        this.user.getUser().get().setName(name);
        this.user.getUser().get().setEmail(email);
    }

    public void updateUserPassword(String newPass) {
        this.user.getUser().get().setPassword(newPass);
    }

    public void updateConfirmation() {
        if (UserAlerts.alertUpdateConfirmation()) {
            this.user.updateUserInDB();
            this.goToMenu();
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
