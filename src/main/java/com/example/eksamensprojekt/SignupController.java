package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Services.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    Login login = new Login();
    @FXML
    TextField Username, knownUsername;
    @FXML
    TextField Password, knownPassword;
    @FXML
    Button SignUpButton;

    @FXML
    public void goToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void CreateUser(ActionEvent event) throws IOException {
        login.makeProfile(Username.getText(), Password.getText());
        goToMain(event);
    }
}
