package com.SmartQuests.leaves;

import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WaitABit extends LeafTask {
    @Override
    public void execute() {
        Execution.delay(2000);
    }
}
