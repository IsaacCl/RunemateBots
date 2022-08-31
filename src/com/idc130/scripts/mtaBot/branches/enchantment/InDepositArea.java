package com.idc130.scripts.mtaBot.branches.enchantment;

import com.idc130.scripts.mtaBot.leaves.enchantment.DepositOrbs;
import com.idc130.scripts.mtaBot.leaves.enchantment.GoToDepositArea;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class InDepositArea extends BranchTask {

    private static final Area depositArea = Area.rectangular(new Coordinate(3361, 9642, 0), new Coordinate(3365, 9638, 0));

    @Override
    public boolean validate() {
        return depositArea.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new DepositOrbs();
    }

    @Override
    public TreeTask failureTask() {
        return new GoToDepositArea();
    }
}
