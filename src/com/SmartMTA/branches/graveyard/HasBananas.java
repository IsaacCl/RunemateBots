package com.SmartMTA.branches.graveyard;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasBananas extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.getItems("Banana", "Peach").size() > 0;
    }

    @Override
    public TreeTask successTask() {
        return new ShouldEatBanana();
    }

    @Override
    public TreeTask failureTask() {
        return new HasBones();
    }
}
