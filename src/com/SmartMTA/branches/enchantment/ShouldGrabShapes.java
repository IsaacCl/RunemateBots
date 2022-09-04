package com.SmartMTA.branches.enchantment;

import com.SmartMTA.leaves.enchantment.CastEnchant;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldGrabShapes extends BranchTask {
    @Override
    public boolean validate() {
        return !Inventory.isFull();
    }

    @Override
    public TreeTask successTask() {
        return new InShapesSection();
    }

    @Override
    public TreeTask failureTask() {
        return new CastEnchant();
    }
}
