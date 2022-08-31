package com.idc130.scripts.mtaBot.branches.enchantment;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class NeedToDeposit extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.isFull() && !Inventory.contains("Cube");
    }

    @Override
    public TreeTask successTask() {
        return new InDepositArea();
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldGrabShapes();
    }
}
