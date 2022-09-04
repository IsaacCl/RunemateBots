package com.SmartMTA.branches.telekinetic;

import com.SmartMTA.leaves.telekinetic.MoveToMaze;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AtMaze extends BranchTask {
    @Override
    public boolean validate() {
        var wall = GameObjects.newQuery().ids(10755).results().nearest();

        if (wall != null) {
            return wall.distanceTo(Players.getLocal()) < 3;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new IsMazeComplete();
    }

    @Override
    public TreeTask failureTask() {
        return new MoveToMaze();
    }
}
