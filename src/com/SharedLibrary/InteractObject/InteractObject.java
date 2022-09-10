package com.SharedLibrary.InteractObject;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class InteractObject extends BranchTask {
    private final SmartObject object;

    public InteractObject(SmartObject object) {
        this.object = object;
    }

    @Override
    public boolean validate() {
        return object.isVisible() && object.isNextToPlayer();
    }

    @Override
    public TreeTask successTask() {
        return new ClickOnObject(object);
    }

    @Override
    public TreeTask failureTask() {
        return new AtCorrectArea(object);
    }
}

