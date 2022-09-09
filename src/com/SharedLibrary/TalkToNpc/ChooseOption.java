package com.SharedLibrary.TalkToNpc;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ChooseOption extends LeafTask {
    @Override
    public void execute() {
        var chatOptions = ChatDialog.getOptions();
        if (chatOptions.get(0).select()) {
            Execution.delay(500, 1000);
        }
    }
}
