package com.idc130.scripts.MTABot.branches.telekinetic;

import com.idc130.scripts.MTABot.utils.Maze;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInLobbyTelekinetic extends BranchTask {
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask successTask() {
        return null;
    }

    @Override
    public TreeTask failureTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                var maze = new Maze();
                maze.print();
                Execution.delay(2000);
            }
        };
    }
}
