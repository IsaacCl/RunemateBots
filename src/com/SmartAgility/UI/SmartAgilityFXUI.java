package com.SmartAgility.UI;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Java FX Gui for configuring bot settings
 */
public class SmartAgilityFXUI extends GridPane implements Initializable {

    public SmartAgilityFXUI(SmartAgility bot) {
        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();

        // Input your Settings GUI FXML file location here.
        // NOTE: DO NOT FORGET TO ADD IT TO MANIFEST AS A RESOURCE
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/SmartAgility/UI/SmartAgilityGUI.fxml"));

        // Set SuicideCowsFXUIController as the class that will be handling our events
        loader.setController(new SmartAgilityFXUIController(bot));

        // Set the FXML load's root to this class
        // NOTE: By setting the root to (this) you must change your .fxml to reflect fx:root
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            bot.getLogger().severe(e);
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setVisible(true);
    }
}