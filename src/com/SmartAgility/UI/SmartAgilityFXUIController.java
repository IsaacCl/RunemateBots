package com.SmartAgility.UI;

import com.SmartAgility.Helpers.CustomPlayerSense;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Java FX Controller for the SuicideCowsFXUI class
 * <p>
 * The controller class is where the logic and implementation of GUI events go.
 * Ex. If you press on a Start button in a typical program, you'd expect the program to actually start.
 * That event handling would be found here.
 * NOTE:   You can assign a single class to be the FXML Loader And Controller.
 * To do this, just set your FXML's loader to .setController(this) in appropriate class.
 */
public class SmartAgilityFXUIController implements Initializable {
    private final SmartAgility bot;

    @FXML
    private Button StartButton;

    @FXML
    private ToggleButton Gnome, Draynor, AlKharid, Varrock, Canifis, Seers, Pollvineach;

    @FXML
    private CheckBox Food, Potions, UseBoredom, UseSeers, Clean, MakePotion, MakeHeadless, MakeBows, LogOut;

    @FXML
    private ComboBox<String> FoodType, FoodAmount, PotionType, PotionAmount, EatFoodHealth;

    @FXML
    private TextField MaxRuntime;

    @FXML
    private TabPane Steps;

    @FXML
    private AnchorPane StartPane;

    public SmartAgilityFXUIController(SmartAgility bot) {
        this.bot = bot;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // If the Start Button is pressed, handle that even in the getStart_BTAction method
        StartButton.setOnAction(StartButton());

        ObservableList<String> foodOptions =
                FXCollections.observableArrayList(
                        "Trout",
                        "Lobster",
                        "Shark",
                        "Swordfish",
                        "Monkfish"
                );

        FoodType.setItems(foodOptions);

        ObservableList<String> potionOptions =
                FXCollections.observableArrayList(
                        "Energy potion",
                        "Super energy",
                        "Stamina potion"
                );


        PotionType.setItems(potionOptions);

        for (int i = 0; i < 7; i++) {
            FoodAmount.getItems().add(i + "");
            PotionAmount.getItems().add(i + "");
        }

        for (int i = 5; i < 20; i++) {
            EatFoodHealth.getItems().add(i + "");
        }

        FoodType.getSelectionModel().selectFirst();
        PotionType.getSelectionModel().selectFirst();
        FoodAmount.getSelectionModel().selectFirst();
        PotionAmount.getSelectionModel().selectFirst();
        EatFoodHealth.getSelectionModel().select(CustomPlayerSense.Key.EATING_FOOD_BUFFER.getAsInteger() + 3);

        InputStream is = Resources.getAsStream("com/SmartAgility/UI/logo.png");

        ImageView logoView = new ImageView();
        logoView.setImage(new Image(is));
        logoView.setFitHeight(270);
        logoView.setFitWidth(600);
        logoView.setLayoutY(28);
        StartPane.getChildren().add(logoView);
    }

    public EventHandler<ActionEvent> StartButton() {
        return event ->
        {
            //options
            bot.guiData.eatFood = Food.isSelected();
            bot.guiData.drinkPotions = Potions.isSelected();
            bot.guiData.MaxRuntime = getInteger(MaxRuntime);
            bot.guiData.UseBoredomSystem = UseBoredom.isSelected();
            bot.guiData.UseSeersTeleport = UseSeers.isSelected();

            bot.guiData.setFoodHealth(Integer.parseInt(EatFoodHealth.getValue()));
            bot.guiData.randomiseNextFoodHealth();

            //bank
            bot.guiData.PotionType = PotionType.getValue();
            bot.guiData.FoodType = FoodType.getValue();
            bot.guiData.PotionAmount = Integer.parseInt(PotionAmount.getValue());
            bot.guiData.FoodAmount = Integer.parseInt(FoodAmount.getValue());

            //courses
            bot.guiData.doDraynor = Draynor.isSelected();
            bot.guiData.doAlKharid = AlKharid.isSelected();
            bot.guiData.doGnome = Gnome.isSelected();
            bot.guiData.doSeers = Seers.isSelected();
            bot.guiData.doVarrock = Varrock.isSelected();
            bot.guiData.doCanifis = Canifis.isSelected();
            bot.guiData.doPollnivneach = Pollvineach.isSelected();

            // Start the timer
            bot.guiData.currentTime.start();

            // Initialize all variables in your bot
            bot.guiData.GUIWait = false;

            bot.loadCourses();

            // Set the EmbeddableUI property to reflect your Info GUI
            Platform.runLater(bot::setToInfoProperty);

        };
    }

    public EventHandler<ActionEvent> NextStep() {
        return event -> Steps.getSelectionModel().selectNext();
    }

    public EventHandler<ActionEvent> PreviousStep() {
        return event -> Steps.getSelectionModel().selectPrevious();
    }

    private int getInteger(TextField input) {
        return Integer.parseInt(input.getCharacters().toString());
    }

    private String getString(TextField input) {
        return input.getCharacters().toString();
    }
}
