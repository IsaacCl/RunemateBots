package com.idc130.scripts.mtaBot.branches.telekinetic;

import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsStatueMoving extends BranchTask {
    @Override
    public boolean validate() {
        var mazeGuardian = Npcs.newQuery().names("Maze Guardian").results().first();

        return mazeGuardian == null || mazeGuardian.isMoving();
    }

    @Override
    public TreeTask successTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                Execution.delayUntil(() -> {
                    var mazeGuardian = Npcs.newQuery().names("Maze Guardian").results().first();
                    return mazeGuardian != null && !mazeGuardian.isMoving();
                }, 500, 4000);
            }
        };
    }

    @Override
    public TreeTask failureTask() {
        return new AtCorrectSide();
    }
}
