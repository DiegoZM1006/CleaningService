module com.example.ti2_cyedi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ti2_cyedi to javafx.fxml;
    exports com.example.ti2_cyedi;
    exports com.example.ti2_cyedi.controllers;
    opens com.example.ti2_cyedi.controllers to javafx.fxml;
}