package com.idc130.scripts.MTABot.branches.alchemist;

import com.idc130.scripts.MTABot.leaves.alchemist.GoToCupboard;
import com.idc130.scripts.MTABot.leaves.alchemist.PickAlchemistItem;
import com.idc130.scripts.MTABot.state.AlchemistGameState;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCloseToCupboard extends BranchTask {
    @Override
    public boolean validate() {
        var location = AlchemistGameState.getBestCupboardArea2();

        return location.contains(Players.getLocal()) || location.isVisible();
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
