package com.SharedLibrary.FightObject;

import com.SharedLibrary.InteractObject.AtCorrectArea;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SharedLibrary.SharedLeaves.DoNothing;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class FightObject extends BranchTask {

    private final SmartObject object;

    public FightObject(SmartObject object) {
        this.object = object;
    }

    @Override
    public boolean validate() {
        return object.isFighting(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new DoNothing("Waiting for fight to be over");
    }

    @Override
    public TreeTask failureTask() {
        return new AtCorrectArea(object);
    }
}
