package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DrinkEnergyDrink extends LeafTask {

    private final SmartAgility bot;

    public DrinkEnergyDrink() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.guiData.currentTask = "Drinking an energy drink.";

        bot.getLogger().debug("Drinking an energy drink.");
        bot.inventoryManager.drinkEnergyDrinks();
    }
}
