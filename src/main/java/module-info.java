module imd.ufrn.br.purposesong {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens imd.ufrn.br.purposesong to javafx.fxml;
    exports imd.ufrn.br.purposesong;
    exports imd.ufrn.br.purposesong.view;
    opens imd.ufrn.br.purposesong.view to javafx.fxml;
}