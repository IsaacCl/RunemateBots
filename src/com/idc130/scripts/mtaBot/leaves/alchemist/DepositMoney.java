package com.idc130.scripts.mtaBot.leaves.alchemist;

import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DepositMoney extends LeafTask {
    @Override
    public void execute() {
        var coinCollector = GameObjects.newQuery().names("Coin Collector").results().nearest();

        if (coinCollector != null) {
            coinCollector.click();
        }
    }
}
