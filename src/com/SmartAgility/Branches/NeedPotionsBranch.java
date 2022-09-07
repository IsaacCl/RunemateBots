package com.SmartAgility.Branches;

import com.SmartAgility.Game.CustomBank;
import com.SmartAgility.Leaves.EmptyLeaf;
import com.SmartAgility.Leaves.WithdrawPotions;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class NeedPotionsBranch extends BranchTask {

    private final SmartAgility bot;
    private final WithdrawPotions withdrawPotions = new WithdrawPotions();
    private final EmptyLeaf emptyLeaf = new EmptyLeaf("Need potions branch");

    public NeedPotionsBranch() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return bot.inventoryManager.needEnergyDrinks() && CustomBank.HasPotion;
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return withdrawPotions;
    }
}