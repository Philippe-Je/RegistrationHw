module org.example.registrationhw {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.registrationhw to javafx.fxml;
    exports org.example.registrationhw;
}