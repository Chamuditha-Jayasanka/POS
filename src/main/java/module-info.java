module com.example.project001 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project001 to javafx.fxml;
    exports com.example.project001;
}