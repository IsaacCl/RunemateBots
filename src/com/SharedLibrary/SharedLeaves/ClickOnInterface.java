package com.SharedLibrary.SharedLeaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ClickOnInterface extends LeafTask {

    private final int id;

    public ClickOnInterface(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        var interfaceComponent = Interfaces.newQuery().ids(id).results().first();

        if (interfaceComponent != null) {
            interfaceComponent.click();
        }
    }
}
