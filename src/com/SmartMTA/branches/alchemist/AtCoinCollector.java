package com.SmartMTA.branches.alchemist;

import com.SmartMTA.leaves.alchemist.DepositMoney;
import com.SmartMTA.leaves.alchemist.WalkToCoinCollector;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AtCoinCollector extends BranchTask {
    private static final Area coinCollectorArea = Area.rectangular(new Coordinate(3366, 9649, 2), new Coordinate(3363, 9648, 2));

    @Override
    public boolean validate() {
        return coinCollectorArea.contains(Players.getLocal());
    }


    @Override
    public TreeTask successTask() {
        return new DepositMoney();
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToCoinCollector();
    }
}
