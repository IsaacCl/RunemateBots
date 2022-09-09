package com.SharedLibrary.SharedLeaves;

import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DoNothing extends LeafTask {

    private final Object reason;

    public DoNothing(String reason) {
        this.reason = reason;
    }

    @Override
    public void execute() {
        System.out.println(reason);
        Execution.delay(1000, 2000);
    }
}
