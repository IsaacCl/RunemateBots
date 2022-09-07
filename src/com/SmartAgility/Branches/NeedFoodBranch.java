package com.SmartAgility.Branches;

import com.SmartAgility.Game.CustomBank;
import com.SmartAgility.Leaves.WithdrawFood;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class NeedFoodBranch extends BranchTask {

    private final SmartAgility bot;
    private final NeedPotionsBranch needPotionsBranch = new NeedPotionsBranch();
    private final WithdrawFood withdrawFood = new WithdrawFood();

    public NeedFoodBranch() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return bot.inventoryManager.needFood() && CustomBank.HasFood;
    }

    @Override
    public TreeTask failureTask() {
        return needPotionsBranch;
    }

    @Override
    public TreeTask successTask() {
        return withdrawFood;
    }
}