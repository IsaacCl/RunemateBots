package com.SmartMTA.branches.alchemist;

import com.SmartMTA.leaves.alchemist.DropSomeItems;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class InventoryTooFull extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public TreeTask successTask() {
        return new DropSomeItems();
    }

    @Override
    public TreeTask failureTask() {
        return new TooMuchMoney();
    }
}
