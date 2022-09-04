package com.SmartMTA.leaves.enchantment;

import com.SmartMTA.utils.WalkingUtils;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.List;

public class GoToDepositArea extends LeafTask {

    private static final Area depositArea = Area.absolute(List.of(new Coordinate[]{new Coordinate(3362, 9641, 0), new Coordinate(3363, 9641, 0), new Coordinate(3362, 9640, 0)}));

    @Override
    public void execute() {
        WalkingUtils.walkToLocalArea(depositArea, "deposit area");
    }
}
