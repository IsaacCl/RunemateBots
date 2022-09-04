package com.SmartMTA.branches.alchemist;

import com.SmartMTA.leaves.alchemist.AlchItem;
import com.SmartMTA.state.AlchemistGameState;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasAlchemistItem extends BranchTask {

    @Override
    public boolean validate() {
        return Inventory.contains(AlchemistGameState.getBestItem());
    }

    @Override
    public TreeTask successTask() {
        return new AlchItem();
    }

    @Override
    public TreeTask failureTask() {
        return new InventoryTooFull();
    }
}
