package com.SharedLibrary.InteractObject;

import com.runemate.game.api.script.framework.tree.LeafTask;

public class ClickOnObject extends LeafTask {
    private final SmartObject object;

    public ClickOnObject(SmartObject object) {
        this.object = object;
    }

    @Override
    public void execute() {
        object.clickOn();
    }
}
