package com.idc130.scripts.mtaBot.branches.telekinetic;

import com.idc130.scripts.mtaBot.leaves.telekinetic.FaceMazeGuardian;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsMazeGuardianVisible extends BranchTask {

    private final TreeTask successTask;

    public IsMazeGuardianVisible(TreeTask successTask) {
        this.successTask = successTask;
    }

    @Override
    public boolean validate() {
        var mazeGuardian = Npcs.newQuery().names("Maze Guardian").results().first();

        return mazeGuardian != null && mazeGuardian.isVisible();
    }

    @Override
    public TreeTask successTask() {
        return successTask;
    }

    @Override
    public TreeTask failureTask() {
        return new FaceMazeGuardian();
    }
}
