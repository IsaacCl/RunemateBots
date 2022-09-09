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
        return object.atCorrectArea();
    }

    @Override
    public TreeTask successTask() {
        return new ObjectIsVisible(object);
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToObject(object);
    }
}
