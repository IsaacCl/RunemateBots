package com.idc130.scripts.mtaBot.branches.telekinetic;

import com.idc130.scripts.mtaBot.leaves.telekinetic.MoveToMaze;
import com.idc130.scripts.mtaBot.utils.Maze;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CanBuildMaze extends BranchTask {
    private final Maze maze;

    public CanBuildMaze() {
        maze = new Maze();
    }

    @Override
    public boolean validate() {
        return maze.failedToBuild;
    }

    @Override
    public TreeTask successTask() {
        return new MoveToMaze();
    }

    @Override
    public TreeTask failureTask() {
        if (maze.failedToBuild || maze.getNextMove() == null) {
            return new MoveToMaze();
        }
        return new AtCorrectSide(maze);
    }
}
