package com.SharedLibrary.InteractObject;

import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkToObject extends LeafTask {
    private final SmartObject object;

    public WalkToObject(SmartObject object) {
        this.object = object;
    }

    @Override
    public void execute() {
        object.walkToObject();
    }
}
