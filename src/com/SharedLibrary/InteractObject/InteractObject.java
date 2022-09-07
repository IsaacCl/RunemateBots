package com.SharedLibrary.InteractObject;

import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractObject extends LeafTask {
    private final SmartObject object;

    public InteractObject(SmartObject object) {
        this.object = object;
    }

    @Override
    public void execute() {
        object.clickOn();
    }
}
