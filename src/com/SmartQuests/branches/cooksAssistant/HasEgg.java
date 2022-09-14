package com.SmartQuests.branches.cooksAssistant;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasEgg extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.contains("Egg");
    }

    @Override
    public TreeTask successTask() {
        return new HasMilk();
    }

    @Override
    public TreeTask failureTask() {
        return new AreaHasEggs();
    }
}
