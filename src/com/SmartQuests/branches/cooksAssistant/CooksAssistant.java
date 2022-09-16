package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.Quest;
import com.runemate.game.api.hybrid.local.Quests;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CooksAssistant extends BranchTask {
    @Override
    public boolean validate() {

        return Quests.get("Cook's Assistant").getStatus() == Quest.Status.IN_PROGRESS;
    }

    @Override
    public TreeTask successTask() {
        return new HasPot();
    }

    @Override
    public TreeTask failureTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3206, 3217, 0), new Coordinate(3210, 3212, 0)), "Cook", "Npc"));
    }
}
