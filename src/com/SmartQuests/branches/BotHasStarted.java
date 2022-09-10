package com.SmartQuests.branches;

import com.SharedLibrary.SharedLeaves.DoNothing;
import com.SmartQuests.SmartQuests;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class BotHasStarted extends BranchTask {
    @Override
    public boolean validate() {
        return SmartQuests.started;
    }

    @Override
    public TreeTask successTask() {
        return new IsEngagedInConversation();
    }

    @Override
    public TreeTask failureTask() {
        return new DoNothing("Waiting for bot to start");
    }
}
