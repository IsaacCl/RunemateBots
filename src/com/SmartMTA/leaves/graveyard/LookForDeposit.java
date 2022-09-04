package com.SmartMTA.leaves.graveyard;

import com.SmartMTA.utils.WalkingUtils;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class LookForDeposit extends LeafTask {
    @Override
    public void execute() {
        var foodChute = GameObjects.newQuery().names("Food chute").results().nearest();
        var depositArea = new Area.Rectangular(new Coordinate(3356, 9640, 1), new Coordinate(3356, 9639, 1));

        Camera.concurrentlyTurnTo(foodChute);
        WalkingUtils.walkToLocalArea(depositArea, "deposit area");
    }
}
