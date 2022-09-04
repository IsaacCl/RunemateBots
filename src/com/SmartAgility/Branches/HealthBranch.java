package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.DrinkEnergyDrink;
import com.SmartAgility.Leaves.EatFood;
import com.SmartAgility.Leaves.EmptyLeaf;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HealthBranch extends BranchTask {

    private EatFood eatFood = new EatFood();
    private EnergyBranch energyBranch = new EnergyBranch();


    private SmartAgility bot;
    public HealthBranch() { bot = (SmartAgility) Environment.getBot(); }

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
