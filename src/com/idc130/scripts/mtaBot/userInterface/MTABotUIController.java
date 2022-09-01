package com.idc130.scripts.mtaBot.userInterface;

import com.idc130.scripts.mtaBot.MTABot;
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
    private Button goButton;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        minigameSelector.getItems().add("alchemist");
        minigameSelector.getItems().add("enchantment");
        minigameSelector.getItems().add("telekinetic");
        minigameSelector.getItems().add("graveyard");

        alchemySelector.getItems().add("high alchemy");
        alchemySelector.getItems().add("low alchemy");

        enchantSelector.getItems().add("Enchant level 1");
        enchantSelector.getItems().add("Enchant level 2");
        enchantSelector.getItems().add("Enchant level 3");
        enchantSelector.getItems().add("Enchant level 4");
        enchantSelector.getItems().add("Enchant level 5");
        enchantSelector.getItems().add("Enchant level 6");
        enchantSelector.getItems().add("Enchant level 7");

        goButton.setOnAction(event ->
        {
            MTABot.minigame = minigameSelector.getValue();
            MTABot.alchemySpell = alchemySelector.getValue();
            MTABot.enchantSpell = enchantSelector.getValue();
        });
    }

}