package com.idc130.scripts.mtaBot;

import com.idc130.scripts.mtaBot.branches.lobby.ShouldStartBot;
import com.idc130.scripts.mtaBot.state.AlchemistGameState;
import com.idc130.scripts.mtaBot.userInterface.MTABotUIController;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.local.hud.interfaces.Chatbox;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.listeners.ChatboxListener;
import com.runemate.game.api.script.framework.listeners.events.MessageEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;


/*
Design principles:
- Maximum of one action per cycle.
- Some execution delay per cycle for performance reasons unless click spamming.
- Prefer events for tracking game state over recording ingame events
- As much logic in tree as possible
 */


public class MTABot extends TreeBot implements EmbeddableUI, ChatboxListener {

    public static String minigame = "";
    public static String alchemySpell = "";
    public static String enchantSpell = "";

    private ObjectProperty<Node> botInterfaceProperty;
    private long lastExecutionTime = 0;

    public MTABot() {
        setEmbeddableUI(this);
    }

    @Override
    public void onStart(java.lang.String... arguments) {
        getEventDispatcher().addListener(this);
    }

    @Override
    public void onStop() {
        getEventDispatcher().removeListener(this);
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        // Because of RuneMate event bug
        if (System.currentTimeMillis() - lastExecutionTime < 1000) {
            return;
        }
        lastExecutionTime = System.currentTimeMillis();

        String message = messageEvent.getMessage();
        if (messageEvent.getType() == Chatbox.Message.Type.SERVER) {
            if (message.contains("boots")) {
                AlchemistGameState.justPickedUp("Leather boots");
            } else if (message.contains("kiteshield")) {
                AlchemistGameState.justPickedUp("Adamant kiteshield");
            } else if (message.contains("helm")) {
                AlchemistGameState.justPickedUp("Adamant med helm");
            } else if (message.contains("Emerald")) {
                AlchemistGameState.justPickedUp("Emerald");
            } else if (message.contains("longsword")) {
                AlchemistGameState.justPickedUp("Rune longsword");
            } else if (message.contains("empty")) {
                AlchemistGameState.justPickedUp("");
            }
        }
    }

    @Override
    public TreeTask createRootTask() {
        return new ShouldStartBot();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new MTABotUIController());
            try {
                Node node = loader.load(Resources.getAsStream("com/idc130/scripts/mtaBot/userInterface/MTABotUIView.fxml"));
                botInterfaceProperty = new SimpleObjectProperty<>(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return botInterfaceProperty;
    }
}