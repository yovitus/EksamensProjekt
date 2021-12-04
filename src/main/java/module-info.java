module com.example.eksamensprojekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eksamensprojekt to javafx.fxml;
    exports com.example.eksamensprojekt;
    exports com.example.eksamensprojekt.Controllers;
    opens com.example.eksamensprojekt.Controllers to javafx.fxml;
}