module com.example.tp_poo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp_poo to javafx.fxml;
    exports com.example.tp_poo;
}