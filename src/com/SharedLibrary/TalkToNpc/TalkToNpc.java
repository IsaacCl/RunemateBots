package com.SharedLibrary.TalkToNpc;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class TalkToNpc extends BranchTask {
    @Override
    public boolean validate() {
        return ChatDialog.getContinue() != null;
    }

    @Override
    public TreeTask successTask() {
        System.out.println("Press continue");

        return new PressContinue();
    }

    @Override
    public TreeTask failureTask() {
        if (ChatDialog.getOptions().size() > 0) {
            System.out.println("Press " + ChatDialog.getOptions().get(0).getText());
        }

        return new ChooseOption();
    }
}
