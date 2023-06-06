package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;

public class MenuViewModel {
    // Singleton
    private static MenuViewModel instance = new MenuViewModel();

    private MenuViewModel() {
    }

    public static MenuViewModel getInstance() {
        return MenuViewModel.instance;
    }

    // Fields
    private App app = App.getInstance();

    // !Only VIP users have acess to this method
    public void goToRegister() {
        this.app.changeToRegisterScene();
    }
}
