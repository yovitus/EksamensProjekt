package com.example.eksamensprojekt;

import com.example.eksamensprojekt.Services.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    Label loginFailMessage;
    Login login = new Login();
    @FXML
    TextField knownUsername;
    @FXML
    TextField knownPassword;
    @FXML
    public void goToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void goToStartside(ActionEvent event) throws IOException {
        login.login(knownUsername.getText(), knownPassword.getText());
        System.out.println(login.accepted);
        if (login.accepted) {
            Parent root = FXMLLoader.load(getClass().getResource("Startside.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            loginFailMessage.setText("WRONG USERNAME OR PASSWORD");
        }
    }

    @FXML
    public void goToSignup(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}