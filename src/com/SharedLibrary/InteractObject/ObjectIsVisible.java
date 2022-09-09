package com.SharedLibrary.InteractObject;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ObjectIsVisible extends BranchTask {
    private final SmartObject object;

    public ObjectIsVisible(SmartObject object) {
        this.object = object;
    }

    @Override
    public boolean validate() {
        return object.isVisible();
    }

    @Override
    public TreeTask successTask() {
        return new ClickOnObject(object);
    }

    @Override
    public TreeTask failureTask() {
        return new TurnToObject(object);
    }
}
