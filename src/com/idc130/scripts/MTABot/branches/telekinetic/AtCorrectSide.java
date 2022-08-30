package com.idc130.scripts.MTABot.branches.telekinetic;

import com.idc130.scripts.MTABot.leaves.telekinetic.CastTelekineticGrab;
import com.idc130.scripts.MTABot.leaves.telekinetic.MoveToCorrectSize;
import com.idc130.scripts.MTABot.leaves.telekinetic.MoveToMaze;
import com.idc130.scripts.MTABot.utils.Maze;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AtCorrectSide extends BranchTask {

    private final Maze maze;

    public AtCorrectSide() {
        maze = new Maze();
    }

    @Override
    public boolean validate() {
        return !maze.failedToBuild && maze.getNextMove().contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new CastTelekineticGrab();
    }

    @Override
    public TreeTask failureTask() {
        if (maze.failedToBuild) {
            return new MoveToMaze();
        }
        return new MoveToCorrectSize(maze.getNextMove(), maze.getNextMoveString());
    }
}
