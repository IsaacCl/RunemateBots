package com.SharedLibrary.TalkToNpc;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class PressContinue extends LeafTask {
    @Override
    public void execute() {
        var continueDialog = ChatDialog.getContinue();
        if (continueDialog != null) {
            continueDialog.select();
            Execution.delayUntil(() -> ChatDialog.getContinue() == null, 500, 1000);
        }
    }
}
