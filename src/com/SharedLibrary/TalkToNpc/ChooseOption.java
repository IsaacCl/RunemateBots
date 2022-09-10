package com.SharedLibrary.TalkToNpc;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ChooseOption extends LeafTask {
    @Override
    public void execute() {
        var chatOptions = ChatDialog.getOptions();
        var yesOption = chatOptions.stream().filter(option -> option.getText().contains("yes")).findFirst();
        var index = 0;
        if (yesOption.isPresent()) {
            index = chatOptions.indexOf(yesOption.get());
        }
        if (chatOptions.get(index).select()) {
            Execution.delay(500, 1000);
        }
    }
}
