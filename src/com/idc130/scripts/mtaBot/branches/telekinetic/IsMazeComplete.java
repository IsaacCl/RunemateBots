package com.idc130.scripts.mtaBot.branches.telekinetic;

import com.idc130.scripts.mtaBot.leaves.telekinetic.TalkToMazeGuardian;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsMazeComplete extends BranchTask {

    @Override
    public boolean validate() {
        var goalPosition = GameObjects.newQuery().ids(23672).results().first();
        var mazeGuardian = Npcs.newQuery().names("Maze Guardian").results().first();

        if (goalPosition == null) return false;
        if (mazeGuardian == null) return false;
        return goalPosition.distanceTo(mazeGuardian) == 0;
    }

    @Override
    public TreeTask successTask() {
        return new IsMazeGuardianVisible(new TalkToMazeGuardian());
    }

    @Override
    public TreeTask failureTask() {
        return new IsStatueMoving();
    }
}
