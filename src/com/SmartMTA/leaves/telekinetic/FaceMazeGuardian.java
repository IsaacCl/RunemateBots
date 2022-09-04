package com.SmartMTA.leaves.telekinetic;

import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class FaceMazeGuardian extends LeafTask {
    @Override
    public void execute() {
        var mazeGuardian = Npcs.newQuery().names("Maze Guardian").results().first();

        if (mazeGuardian != null) {
            Camera.turnTo(mazeGuardian);
        }
    }
}
