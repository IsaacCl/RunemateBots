package com.SmartQuests;

import com.SmartQuests.branches.BotHasStarted;
import com.SmartQuests.userInterface.SmartQuestsUIController;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class SmartQuests extends TreeBot implements EmbeddableUI {
    public static boolean started = false;

    private ObjectProperty<Node> botInterfaceProperty;

    public SmartQuests() {
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask() {
        return new BotHasStarted();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new SmartQuestsUIController());
            try {
                Node node = loader.load(Resources.getAsStream("com/SmartQuests/userInterface/SmartQuestsUIView.fxml"));
                botInterfaceProperty = new SimpleObjectProperty<>(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return botInterfaceProperty;
    }
}