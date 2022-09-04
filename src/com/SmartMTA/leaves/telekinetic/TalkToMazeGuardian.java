package com.SmartMTA.leaves.telekinetic;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TalkToMazeGuardian extends LeafTask {
    @Override
    public void execute() {
        Environment.getLogger().info("Talk to maze guardian");

        var mazeGuardian = Npcs.newQuery().names("Maze Guardian").results().first();

        var continueDialog = ChatDialog.getContinue();
        var chatOptions = ChatDialog.getOptions();
        if (continueDialog != null) {
            continueDialog.select();
            Execution.delayUntil(() -> ChatDialog.getContinue() == null, 500, 1000);
        } else if (chatOptions.size() > 0) {
            if (chatOptions.get(0).select()) {
                Execution.delay(500, 1000);
            }
        } else if (mazeGuardian != null) {
            mazeGuardian.click();
            Execution.delayUntil(() -> ChatDialog.getContinue() != null, 500, 1000);
        }
    }

}
