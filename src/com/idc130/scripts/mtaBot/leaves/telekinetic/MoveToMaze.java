package com.idc130.scripts.mtaBot.leaves.telekinetic;

import com.idc130.scripts.mtaBot.utils.WalkingUtils;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class MoveToMaze extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().info("Move to maze");

        var wall = GameObjects.newQuery().ids(10755).results().nearest();

        if (wall != null) {
            var position = wall.getPosition();

            if (position != null) {
                var area = new Area.Rectangular(new Coordinate(position.getX() - 1, position.getY() - 1, 0), new Coordinate(position.getX() + 1, position.getY() + 1, 0));
                WalkingUtils.walkToLocalArea(area, "maze");
            }
        }
    }
}
