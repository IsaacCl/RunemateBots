package com.SmartMTA.branches.alchemist;

import com.SmartMTA.leaves.alchemist.GoToCupboard;
import com.SmartMTA.leaves.alchemist.PickAlchemistItem;
import com.SmartMTA.state.AlchemistGameState;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCloseToCupboard extends BranchTask {
    @Override
    public boolean validate() {
        var location = AlchemistGameState.getBestCupboardArea2();

        return location.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new PickAlchemistItem();
    }

    @Override
    public TreeTask failureTask() {
        return new GoToCupboard();
    }
}
