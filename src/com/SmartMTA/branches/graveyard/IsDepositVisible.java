package com.SmartMTA.branches.graveyard;

import com.SmartMTA.leaves.graveyard.DepositBananas;
import com.SmartMTA.leaves.graveyard.LookForDeposit;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsDepositVisible extends BranchTask {
    @Override
    public boolean validate() {
        var foodChute = GameObjects.newQuery().names("Food chute").results().nearest();
        return foodChute != null && foodChute.isVisible();
    }

    @Override
    public TreeTask successTask() {
        return new DepositBananas();
    }

    @Override
    public TreeTask failureTask() {
        return new LookForDeposit();
    }
}
