package com.SmartMTA.branches.graveyard;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SmartMTA.leaves.graveyard.EatBananas;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldEatBanana extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.getQuantity("Banana", "Peach") > 16 && Health.getCurrentPercent() < 80;
    }

    @Override
    public TreeTask successTask() {
        return new EatBananas();
    }

    @Override
    public TreeTask failureTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3356, 9640, 1), new Coordinate(3356, 9639, 1)), "Food chute", "GameObject"));
    }
}
