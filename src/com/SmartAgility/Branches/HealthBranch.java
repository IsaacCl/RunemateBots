package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.EatFood;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HealthBranch extends BranchTask {

    private final EatFood eatFood = new EatFood();
    private final EnergyBranch energyBranch = new EnergyBranch();


    private final SmartAgility bot;

    public HealthBranch() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return bot.playerManager.hasLowHealth();
    }

    @Override
    public TreeTask successTask() {
        return eatFood;
    }

    @Override
    public TreeTask failureTask() {
        return energyBranch;
    }
}
