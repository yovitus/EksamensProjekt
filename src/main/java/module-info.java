module com.example.eksamensprojekt {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.eksamensprojekt to javafx.fxml, javafx.graphics;
    exports com.example.eksamensprojekt;

}