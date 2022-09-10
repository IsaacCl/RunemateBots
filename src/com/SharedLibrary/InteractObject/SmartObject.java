package com.SharedLibrary.InteractObject;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.details.Interactable;
import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;

public class SmartObject {
    private final Area locationToLookFor;
    private final String type; // NPC or GameObject
    private int id = -1;
    private String name = "";

    public SmartObject(Area locationToLookFor, int id, String type) {
        this.locationToLookFor = locationToLookFor;
        this.id = id;
        this.type = type;
    }

    public SmartObject(Area locationToLookFor, String name, String type) {
        this.locationToLookFor = locationToLookFor;
        this.name = name;
        this.type = type;
    }

    public Interactable getNearestInteractable() {
        switch (type) {
            case "GameObject": {
                if (id != -1) {
                    return GameObjects.newQuery().ids(id).results().nearest();
                }
                return GameObjects.newQuery().names(name).results().nearest();
            }
            case "Npc": {
                if (id != -1) {
                    return Npcs.newQuery().ids(id).results().nearest();
                }
                return Npcs.newQuery().names(name).results().nearest();
            }
            default:
                throw new RuntimeException("This type of smart object doesn't exist");
        }
    }

    public Locatable getNearestLocatable() {
        switch (type) {
            case "GameObject": {
                if (id != -1) {
                    return GameObjects.newQuery().ids(id).results().nearest();
                }
                return GameObjects.newQuery().names(name).results().nearest();
            }
            case "Npc": {
                if (id != -1) {
                    return Npcs.newQuery().ids(id).results().nearest();
                }
                return Npcs.newQuery().names(name).results().nearest();
            }
            default:
                throw new RuntimeException("This type of smart object doesn't exist");
        }
    }

    public boolean clickOn() {
        var object = getNearestInteractable();

        if (object != null) {
            return object.click();
        }
        return false;
    }

    public boolean turnTo() {
        var object = getNearestLocatable();

        if (object != null) {
            return Camera.turnTo(object);
        }
        return false;
    }

    public boolean isVisible() {
        var object = getNearestInteractable();

        return object != null && object.isVisible();
    }

    public boolean atCorrectArea() {
        return locationToLookFor.contains(Players.getLocal());
    }

    public boolean walkToObject() {
        Web web = Traversal.getDefaultWeb();
        var me = Players.getLocal();

        if (web != null && me != null) {
            var path = web.getPathBuilder().buildTo(locationToLookFor);
            if (path != null) {
                Environment.getLogger().info("Walking to " + name);
                if (path.step(true)) {
                    Execution.delayUntil(() -> me.getAnimationId() != -1, 500, 2000);
                    return true;
                }
            } else {
                Environment.getLogger().info("Couldn't build a path to " + name);
            }
        }
        return false;
    }

    public boolean isFighting(Player local) {
        return local != null && local.getTarget() != null && local.getTarget().getName() != null && local.getTarget().getName().equals(name);
    }
}
