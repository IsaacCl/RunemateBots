package com.SharedLibrary.TalkToNpc;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ChooseOption extends LeafTask {
    @Override
    public void execute() {
        var chatOptions = ChatDialog.getOptions();
        var bestOption = chatOptions.stream().filter(option -> {
            var text = option.getText().toLowerCase();
            return text.contains("no, i'm not planning to do that") || text.contains("i know where to find this stuff");
        }).findFirst();
        var index = 0;
        if (bestOption.isPresent()) {
            index = chatOptions.indexOf(bestOption.get());
        }
        if (index < chatOptions.size()) {
            if (chatOptions.get(index).select()) {
                Execution.delay(500, 1000);
            }
        }
    }
}
