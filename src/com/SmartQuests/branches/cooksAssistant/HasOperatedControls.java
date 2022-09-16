package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Chatbox;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasOperatedControls extends BranchTask {
    @Override
    public boolean validate() {
        return Chatbox.newQuery().textContains("You put the grain in the hopper.").results().size() == 0 && Chatbox.newQuery().textContains("There is already grain in the hopper.").results().size() == 0;
    }

    @Override
    public TreeTask successTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3162, 3293, 0), new Coordinate(3159, 3295, 0)), "Wheat", "GameObject"));
    }

    @Override
    public TreeTask failureTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3165, 3305, 2), new Coordinate(3165, 3306, 2)), "Hopper controls", "GameObject"));
    }
}
