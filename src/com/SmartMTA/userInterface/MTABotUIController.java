package com.SmartMTA.userInterface;

import com.SmartMTA.MTABot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;


public class MTABotUIController implements Initializable {

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


        minigameSelector.getItems().add("Alchemists' Playground");
        minigameSelector.getItems().add("Enchanting Chamber");
        minigameSelector.getItems().add("Telekinetic Theatre");
        minigameSelector.getItems().add("Creature Graveyard");
        minigameSelector.setValue("Alchemists' Playground");

        alchemySelector.getItems().add("High alchemy");
        alchemySelector.getItems().add("Low alchemy");
        alchemySelector.setValue("High alchemy");

        enchantSelector.getItems().add("Enchant level 1");
        enchantSelector.getItems().add("Enchant level 2");
        enchantSelector.getItems().add("Enchant level 3");
        enchantSelector.getItems().add("Enchant level 4");
        enchantSelector.getItems().add("Enchant level 5");
        enchantSelector.getItems().add("Enchant level 6");
        enchantSelector.getItems().add("Enchant level 7");
        enchantSelector.setValue("Enchant level 7");

        graveyardSelector.getItems().add("Bones to bananas");
        graveyardSelector.getItems().add("Bones to peaches");
        graveyardSelector.setValue("Bones to bananas");

        goButton.setOnAction(event ->
        {
            MTABot.minigame = minigameSelector.getValue();
            MTABot.alchemySpell = alchemySelector.getValue();
            MTABot.enchantSpell = enchantSelector.getValue();
            MTABot.graveyardSpell = graveyardSelector.getValue();
        });
    }

}