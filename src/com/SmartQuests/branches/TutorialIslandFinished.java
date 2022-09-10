package com.SmartQuests.branches;

import com.SharedLibrary.SharedLeaves.DoNothing;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class TutorialIslandFinished extends BranchTask {
    @Override
    public boolean validate() {
        return Npcs.newQuery().names("Lumbridge Guide").results().size() > 0;
    }

    @Override
    public TreeTask successTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                var bot = Environment.getBot();

                if (bot != null) {
                    bot.stop("Tutorial island is finished.");
                }
            }
        };
    }

    @Override
    public TreeTask failureTask() {
        return new DoNothing("Can't find anything to do");
    }
}
