package com.SmartMTA.branches.enchantment;

import com.SmartMTA.leaves.enchantment.TeleportToEnchanters;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInLobbyEnchantment extends BranchTask {

    @Override
    public boolean validate() {
        var portal = GameObjects.newQuery().names("Enchanters Teleport").results().nearest();
        return portal != null;
    }

    @Override
    public TreeTask successTask() {
        return new TeleportToEnchanters();
    }

    @Override
    public TreeTask failureTask() {
        return new NeedToDeposit();
    }
}
