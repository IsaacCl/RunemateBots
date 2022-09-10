package com.SmartQuests.branches;

import com.SharedLibrary.FightObject.FightObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class RangeRat extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.contains("Shortbow") || Inventory.contains("Bronze arrow");
    }

    @Override
    public TreeTask successTask() {
        return new ClickTwoItems("Shortbow", "Bronze arrow");
    }

    @Override
    public TreeTask failureTask() {
        return new FightObject(new SmartObject(new Area.Rectangular(new Coordinate(3107, 9510, 0), new Coordinate(3108, 9511, 0)), "Giant rat", "Npc"));
    }
}
