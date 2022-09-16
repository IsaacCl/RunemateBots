package com.SmartQuests.branches.cooksAssistant;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasPotOfFlour extends BranchTask {
    @Override
    public boolean validate() {
        return Inventory.contains("Pot of flour");
    }

    @Override
    public TreeTask successTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                System.out.println("Quest completed, time to talk to the Cook");
            }
        };
    }

    @Override
    public TreeTask failureTask() {
        return new ShouldPutWheatInHopper();
    }
}
