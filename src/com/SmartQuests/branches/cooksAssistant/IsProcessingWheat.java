package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.Varps;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsProcessingWheat extends BranchTask {
    @Override
    public boolean validate() {
        return Varps.getAt(695).getValue() == 1; //Has processed flour
    }

    @Override
    public TreeTask successTask() {
        return new HasOperatedControls();
    }

    @Override
    public TreeTask failureTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3162, 3293, 0), new Coordinate(3159, 3295, 0)), "Wheat", "GameObject"));
    }
}
