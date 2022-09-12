package com.SmartQuests.branches.tutorialIsland;

import com.SharedLibrary.SharedLeaves.ClickItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ClickTwoItems extends BranchTask {

    private final String item1;
    private final String item2;

    public ClickTwoItems(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public boolean validate() {
        return Inventory.contains(item1);
    }

    @Override
    public TreeTask successTask() {
        return new ClickItem(item1);
    }

    @Override
    public TreeTask failureTask() {
        return new ClickItem(item2);
    }
}
