package com.idc130.scripts.MTABot.branches.alchemist;

import com.idc130.scripts.MTABot.leaves.alchemist.AlchItem;
import com.idc130.scripts.MTABot.state.AlchemistGameState;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasAlchemistItem extends BranchTask {

    @Override
    public boolean validate() {
        return Inventory.contains(AlchemistGameState.getBestItem());
    }

    @Override
    public TreeTask successTask() {
        return new AlchItem();
    }

    @Override
    public TreeTask failureTask() {
        return new IsCloseToCupboard();
    }
}
