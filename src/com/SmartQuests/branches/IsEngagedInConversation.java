package com.SmartQuests.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsEngagedInConversation extends BranchTask {
    @Override
    public boolean validate() {
        return ChatDialog.getText() != null || ChatDialog.getOptions().size() > 0;
    }

    @Override
    public TreeTask successTask() {
        return new IsContinue();
    }

    @Override
    public TreeTask failureTask() {
        return new HasTaskOverChatbox();
    }
}
