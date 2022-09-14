package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasMilk extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.contains("Bucket of milk");
    }

    @Override
    public TreeTask successTask() {
        return new HasPotOfFlour();
    }

    @Override
    public TreeTask failureTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3171, 3319, 0), new Coordinate(3174, 3317, 0)), "Dairy cow", "GameObject"));
    }
}
