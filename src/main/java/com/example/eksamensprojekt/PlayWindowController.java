package com.example.eksamensprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.io.IOException;

public class PlayWindowController {

        @FXML
        private ToggleGroup Buttons;

        @FXML
        private ToggleButton button_pause;

        @FXML
        private ToggleButton button_play;

        @FXML
        private Text text;

        @FXML
        void toggleButton(ActionEvent event) throws IOException {
       if(event.getSource()==button_play){
           text.setText("Loading...");
       }
       if(event.getSource()==button_pause){
           text.setText("Paused...");
       }
        }

    }


