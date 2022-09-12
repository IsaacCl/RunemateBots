package com.IsaacDeveloperTools;

import com.IsaacDeveloperTools.branches.RootTask;
import com.IsaacDeveloperTools.userInterface.IsaacDeveloperToolsUIController;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

public class IsaacDeveloperTools extends TreeBot implements EmbeddableUI {
    public static boolean started = false;
    public static List<String> nearbyGameObjectNames;
    public static List<String> nearbyNpcNames;
    public static Coordinate currentPlayerPosition;
    private ObjectProperty<Node> botInterfaceProperty;

    public IsaacDeveloperTools() {
        setEmbeddableUI(this);
    }

    public static String getCurrentPosition() {
        return currentPlayerPosition.toString();
    }

    public static String getObjectType(String name) {
        if (nearbyNpcNames.contains(name)) {
            return "Npc";
        } else if (nearbyGameObjectNames.contains(name)) {
            return "GameObject";
        } else return "Not found";
    }

    @Override
    public TreeTask createRootTask() {
        return new RootTask();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterfaceProperty == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new IsaacDeveloperToolsUIController());
            try {
                Node node = loader.load(Resources.getAsStream("com/IsaacDeveloperTools/userInterface/IsaacDeveloperToolsUIView.fxml"));
                botInterfaceProperty = new SimpleObjectProperty<>(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return botInterfaceProperty;
    }
}