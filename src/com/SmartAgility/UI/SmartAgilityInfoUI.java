package com.SmartAgility.UI;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Info GUI for the exampleflaxpicker Bot
 * <p>
 * This will show various live stats on the bot
 */
public class SmartAgilityInfoUI extends GridPane implements Initializable {
    private final SmartAgility bot;

    @FXML
    private Label RunTime, CurrentTask, Course, Experience, ExperiencePH, Marks, MarksPH;

    public SmartAgilityInfoUI(SmartAgility bot) {
        this.bot = bot;

        // Load the fxml file using RuneMate's resources class.
        FXMLLoader loader = new FXMLLoader();

        // Input your InfoUI FXML file location here.
        // NOTE: DO NOT FORGET TO ADD IT TO MANIFEST AS A RESOURCE
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/SmartAgility/UI/InfoUI.fxml"));

        // Set this class as root AND Controller for the Java FX GUI
        loader.setController(this);

        // NOTE: By setting the root to (this) you must change your .fxml to reflect fx:root
        loader.setRoot(this);

        try {
            loader.load(stream.get());
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // An object property is a container of an object, which can be added
    // listeners to. In this case the property contains our controller class
    // (this)

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVisible(true);
    }

    // This method will update the text that is presented to the end user
    public void update() {
        try {
            Experience.textProperty().set(String.valueOf(bot.guiData.experienceGained));
            ExperiencePH.textProperty().set(String.valueOf(bot.guiData.getExperiencePerHour()));
            Marks.textProperty().set(String.valueOf(bot.guiData.marks));
            MarksPH.textProperty().set(String.valueOf(bot.guiData.getMarksPerHour()));

            RunTime.textProperty().set(bot.guiData.currentTime.getRuntimeAsString());
            CurrentTask.textProperty().set(bot.guiData.currentTask);
            Course.textProperty().set(bot.guiData.currentArea);
        } catch (Exception e) {
            bot.getLogger().severe(e);
        }
    }

}