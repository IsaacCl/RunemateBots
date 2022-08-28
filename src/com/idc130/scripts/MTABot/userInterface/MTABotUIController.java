package com.idc130.scripts.MTABot.userInterface;
import com.idc130.scripts.MTABot.MTABot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class MTABotUIController implements Initializable {

    @FXML
    private CheckBox checkDoEnchanters;
    @FXML
    private CheckBox checkDoCreatureGraveyard;
    @FXML
    private CheckBox checkShouldDoAlchemist;
    @FXML
    private ImageView imageView;

    @FXML
    public void initialize(URL location, ResourceBundle resources)
    {
        checkDoCreatureGraveyard.setOnAction(event ->
        {
            MTABot.shouldDoCreatureGraveyard = checkDoCreatureGraveyard.isSelected();

            System.out.println("set shouldDoCreatureGraveyard to " + Boolean.toString(MTABot.shouldDoCreatureGraveyard).toUpperCase(Locale.ROOT));
        });

        checkShouldDoAlchemist.setOnAction(event ->
        {
            MTABot.shouldDoAlchemist = checkShouldDoAlchemist.isSelected();

            System.out.println("set shouldDoAlchemist to " + Boolean.toString(MTABot.shouldDoAlchemist).toUpperCase(Locale.ROOT));
        });

        checkDoEnchanters.setOnAction(event ->
        {
            MTABot.shouldDoEnchanters = checkDoEnchanters.isSelected();

            System.out.println("set shouldDoAlchemist to " + Boolean.toString(MTABot.shouldDoEnchanters).toUpperCase(Locale.ROOT));
        });
    }

}