package com.idc130.scripts.MTABot;

import com.idc130.scripts.MTABot.branches.lobby.ShouldStartBot;
import com.idc130.scripts.MTABot.userInterface.MTABotUIController;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class MTABot extends TreeBot implements EmbeddableUI {

    public static boolean shouldDoCreatureGraveyard = false;
    public static boolean shouldDoAlchemist = false;

    private ObjectProperty<Node> botInterfaceProperty;

    public MTABot()
    {
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask() {
        return new ShouldStartBot();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if(botInterfaceProperty == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new MTABotUIController());
            try {
                Node node = loader.load(Resources.getAsStream("com/idc130/scripts/MTABot/userInterface/MTABotUIView.fxml"));
                botInterfaceProperty = new SimpleObjectProperty<>(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return botInterfaceProperty;
    }
}