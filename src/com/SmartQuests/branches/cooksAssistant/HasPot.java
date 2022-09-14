package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasPot extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.contains("Pot") || Inventory.contains("Pot of flour");
    }

    @Override
    public TreeTask successTask() {
        return new HasBucket();
    }

    @Override
    public TreeTask failureTask() {
        // Pick up pot
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3206, 3217, 0), new Coordinate(3210, 3212, 0)), "Pot", "GroundItem"));
    }
}
