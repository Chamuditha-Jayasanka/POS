module com.example.project001 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project001 to javafx.fxml;
    exports com.example.project001;
}