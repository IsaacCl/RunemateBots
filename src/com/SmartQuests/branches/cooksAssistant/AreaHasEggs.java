package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.FightObject.FightObject;
import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreaHasEggs extends BranchTask {
    @Override
    public boolean validate() {
        return GroundItems.contains("Egg");
    }

    @Override
    public TreeTask successTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3228, 3300, 0), new Coordinate(3231, 3297, 0)), "Egg", "GroundItem"));
    }

    @Override
    public TreeTask failureTask() {
        return new FightObject(new SmartObject(new Area.Rectangular(new Coordinate(3228, 3300, 0), new Coordinate(3231, 3297, 0)), "Chicken", "Npc"));
    }
}
