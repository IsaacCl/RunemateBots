package com.idc130.scripts.mtaBot.branches.graveyard;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasBananas extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.contains("Banana");
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
