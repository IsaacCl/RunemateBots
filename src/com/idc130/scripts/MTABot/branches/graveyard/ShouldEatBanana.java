package com.idc130.scripts.MTABot.branches.graveyard;

import com.idc130.scripts.MTABot.leaves.graveyard.DepositBananas;
import com.idc130.scripts.MTABot.leaves.graveyard.EatBananas;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldEatBanana extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.getQuantity("Banana") > 16 && Health.getCurrentPercent() < 80;
    }

    @Override
    public TreeTask successTask() {
        return new EatBananas();
    }

    @Override
    public TreeTask failureTask() {
        return new DepositBananas();
    }
}
