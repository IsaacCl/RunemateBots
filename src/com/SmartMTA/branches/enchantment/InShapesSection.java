package com.SmartMTA.branches.enchantment;

import com.SmartMTA.leaves.enchantment.GoToShapesSection;
import com.SmartMTA.leaves.enchantment.PickUpShapes;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class InShapesSection extends BranchTask {

    private static final Area cubeArea = Area.rectangular(new Coordinate(3353, 9651, 0), new Coordinate(3347, 9656, 0));

    @Override
    public boolean validate() {
        return cubeArea.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new PickUpShapes();
    }

    @Override
    public TreeTask failureTask() {
        return new GoToShapesSection();
    }
}
