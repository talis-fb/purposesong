package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;

public class SettingsViewModel {
    // Singleton
    private static SettingsViewModel instance = new SettingsViewModel();

    private SettingsViewModel() {

    }

    // Fields
    private App app = App.getInstance();

    public static SettingsViewModel getInstance() {
        return SettingsViewModel.instance;
    }

    public void goToMenu() {
        this.app.changeToMenuScene();
    }
}
