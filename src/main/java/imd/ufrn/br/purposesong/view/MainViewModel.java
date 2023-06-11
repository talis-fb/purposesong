package imd.ufrn.br.purposesong.view;

import imd.ufrn.br.purposesong.App;
import imd.ufrn.br.purposesong.database.inmemory.InMemoryUserRepositoryImpl;
import imd.ufrn.br.purposesong.entity.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainViewModel {
    // Singleton ----------
    private static MainViewModel instance = new MainViewModel();
    private MainViewModel() {}
    public static MainViewModel getInstance() {
        return MainViewModel.instance;
    }
    // -----------------



    // Fields
    private App app = App.getInstance();
    private final StringProperty helloMessage = new SimpleStringProperty("Essa é a tela principal: Aperte para adicionar");

    public StringProperty helloMessage() {
        return helloMessage;
    }

    public String getHelloMessage() {
        return helloMessage.get();
    }

    public void setHelloMessage(String message) {
        helloMessage.set(message);
    }

    public void goToLogin() {

        System.out.println("Begin ->Bancquinho");

        try {
            var ele = new InMemoryUserRepositoryImpl();
            ele.create(new User());
        } catch (Exception e) {
            System.out.println("ERRO");
            System.out.println(e.getMessage());
        }


        System.out.println("END -> Bancquinho");

        this.app.changeToLoginScene();
    }
}
