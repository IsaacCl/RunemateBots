package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.DrinkEnergyDrink;
import com.SmartAgility.Leaves.EmptyLeaf;
import com.SmartAgility.Leaves.PickupItems;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class EnergyBranch extends BranchTask {

    private DrinkEnergyDrink drinkEnergyDrink = new DrinkEnergyDrink();
    private PickupBranch pickupBranch = new PickupBranch();


    private SmartAgility bot;
    public EnergyBranch() { bot = (SmartAgility) Environment.getBot(); }

    @Override
    public boolean validate() {
        return bot.playerManager.hasLowEnergy() && bot.inventoryManager.hasEnergyDrinks();
    }

    @Override
    public TreeTask successTask() {
        return drinkEnergyDrink;
    }

    @Override
    public TreeTask failureTask() {
        return pickupBranch;
    }
}
