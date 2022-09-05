package com.SmartQuests.branches;

import com.SmartQuests.leaves.WaitABit;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsContinue extends BranchTask {
    @Override
    public boolean validate() {
        return ChatDialog.getContinue() != null;
    }

    @Override
    public TreeTask successTask() {
        System.out.println("Press continue");

        return new WaitABit();
    }

    @Override
    public TreeTask failureTask() {
        if (ChatDialog.getOptions().size() > 0) {
            System.out.println("Press " + ChatDialog.getOptions().get(0).getText());
        }

        return new WaitABit();
    }
}
