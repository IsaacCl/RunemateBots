package com.SmartMTA.branches.telekinetic;

import com.SmartMTA.leaves.telekinetic.CastTelekineticGrab;
import com.SmartMTA.leaves.telekinetic.MoveToCorrectSize;
import com.SmartMTA.leaves.telekinetic.MoveToMaze;
import com.SmartMTA.utils.Maze;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AtCorrectSide extends BranchTask {

    private final Maze maze;

    public AtCorrectSide(Maze maze) {
        this.maze = maze;
    }

    @Override
    public boolean validate() {
        if (maze == null) {
            throw new RuntimeException("Maze is null");
        } else if (maze.getNextMove() == null) {
            throw new RuntimeException("next move is null");
        }
        return !maze.failedToBuild && maze.getNextMove().contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new IsMazeGuardianVisible(new CastTelekineticGrab());
    }

    @Override
    public TreeTask failureTask() {
        if (maze.failedToBuild) {
            return new MoveToMaze();
        }
        return new MoveToCorrectSize(maze.getNextMove(), maze.getNextMoveString());
    }
}
