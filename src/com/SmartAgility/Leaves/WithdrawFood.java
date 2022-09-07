package com.SmartAgility.Leaves;

import com.SmartAgility.Game.CustomBank;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WithdrawFood extends LeafTask {

    private final SmartAgility bot;

    public WithdrawFood() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.guiData.currentTask = "Withdrawing food.";

        if (!Bank.isOpen()) {
            Bank.open();
        } else {
            SpriteItem food = Bank.newQuery().names(bot.guiData.FoodType).results().random();

            if (food != null)
                Bank.withdraw(food, bot.guiData.FoodAmount);
            else
                CustomBank.HasFood = false;
        }
    }
}