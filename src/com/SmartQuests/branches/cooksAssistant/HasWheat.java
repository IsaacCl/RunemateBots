package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasWheat extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.contains("Wheat");
    }

    @Override
    public TreeTask successTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3165, 3307, 2), new Coordinate(3167, 3308, 2)), "Hopper", "GameObject"));
    }

    @Override
    public TreeTask failureTask() {
        return new IsProcessingWheat();
    }
}
