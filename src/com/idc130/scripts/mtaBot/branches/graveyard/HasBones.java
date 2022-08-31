package com.idc130.scripts.mtaBot.branches.graveyard;

import com.idc130.scripts.mtaBot.leaves.graveyard.CastBonesToBananas;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasBones extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.getQuantity("Animals' bones") >= 8;
    }

    @Override
    public TreeTask successTask() {
        return new CastBonesToBananas();
    }

    @Override
    public TreeTask failureTask() {
        return new AtBonesArea();
    }
}