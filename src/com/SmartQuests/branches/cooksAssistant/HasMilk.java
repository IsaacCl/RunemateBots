package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SharedLibrary.InteractObject.WaitingCondition;
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
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3171, 3317, 0), new Coordinate(3171, 3318, 0)), "Dairy cow", "GameObject", new WaitingCondition(() -> Inventory.contains("Bucket of milk"), 1000, 4000)));
    }
}
