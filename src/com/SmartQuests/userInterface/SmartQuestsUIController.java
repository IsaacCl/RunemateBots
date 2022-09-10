package com.SmartQuests.userInterface;

import com.SmartQuests.SmartQuests;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;


public class SmartQuestsUIController implements Initializable {

    @FXML
    private ChoiceBox<String> enchantSelector;
    @FXML
    private ChoiceBox<String> alchemySelector;
    @FXML
    private ChoiceBox<String> minigameSelector;
    @FXML
    private ChoiceBox<String> graveyardSelector;
    @FXML
    private Button goButton;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        goButton.setOnAction(event -> SmartQuests.started = true);
    }

}