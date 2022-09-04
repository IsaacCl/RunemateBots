package com.SmartMTA.leaves.enchantment;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DepositOrbs extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().info("Depositing orbs");

        var hole = GameObjects.newQuery().names("Hole").results().nearest();

        if (hole != null) {
            hole.click();
            Execution.delayUntil(() -> !Inventory.isFull(), 100, 1000);
        }
    }
}
