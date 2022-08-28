package com.idc130.scripts.MTABot.leaves.enchantment;

import com.idc130.scripts.MTABot.utils.WalkingUtils;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GoToShapesSection extends LeafTask {

    private static final Area shapesSection = Area.rectangular(new Coordinate(3351, 9654, 0), new Coordinate(3349, 9653, 0));

    @Override
    public void execute() {
        WalkingUtils.walkToLocalArea(shapesSection, "shapes section");
    }
}
