package com.SharedLibrary.SharedLeaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.Objects;
import java.util.concurrent.Callable;

public class ClickOnInterface extends LeafTask {

    private int id = -1;
    private String text = "";

    private Callable<Boolean> waitCondition = null;

    public ClickOnInterface(int id) {
        this.id = id;
    }

    public ClickOnInterface(String text) {
        this.text = text;
    }

    public ClickOnInterface(String text, Callable<Boolean> waitCondition) {
        this.text = text;
        this.waitCondition = waitCondition;
    }

    @Override
    public void execute() {
        InterfaceComponent interfaceComponent;

        if (!Objects.equals(text, "")) {
            interfaceComponent = Interfaces.newQuery().textContains(text).results().first();
        } else {
            interfaceComponent = Interfaces.newQuery().ids(id).results().first();
        }

        if (interfaceComponent != null) {
            interfaceComponent.click();
            if (waitCondition == null) {
                Execution.delay(500, 1000);
            } else {
                Execution.delayUntil(waitCondition, 500, 1500);
            }
        }
    }
}
