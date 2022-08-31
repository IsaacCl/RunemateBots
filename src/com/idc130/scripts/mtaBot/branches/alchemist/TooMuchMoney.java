package com.idc130.scripts.mtaBot.branches.alchemist;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class TooMuchMoney extends BranchTask {
    @Override
    public boolean validate() {
        var coins = Inventory.getItems("Coins");

        if (coins.size() > 0) {
            return coins.get(0).getQuantity() > 1500;
        }

        return false;
    }

    @Override
    public TreeTask successTask() {
        return new AtCoinCollector();
    }

    @Override
    public TreeTask failureTask() {
        return new IsCloseToCupboard();
    }
}
