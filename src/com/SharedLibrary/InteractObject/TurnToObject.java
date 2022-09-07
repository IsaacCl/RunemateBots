package com.SharedLibrary.InteractObject;

import com.runemate.game.api.script.framework.tree.LeafTask;

public class TurnToObject extends LeafTask {
    private final SmartObject object;

    public TurnToObject(SmartObject object) {
        this.object = object;
    }

    @Override
    public void execute() {
        object.turnTo();
    }
}
