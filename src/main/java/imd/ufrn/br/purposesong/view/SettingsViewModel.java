package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;

public class SettingsViewModel {
    private App app = App.getInstance();

    public void goToMenu() {
        this.app.changeToMenuScene();
    }


    // Singleton -----------------------
    private static final SettingsViewModel instance = new SettingsViewModel();
    private SettingsViewModel() {}
    public static SettingsViewModel getInstance() {
        return SettingsViewModel.instance;
    }

}
