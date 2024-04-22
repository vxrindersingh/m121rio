module com.example.m12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.m12 to javafx.fxml;
    exports com.example.m12;
}