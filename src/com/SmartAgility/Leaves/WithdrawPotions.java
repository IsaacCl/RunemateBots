package com.SmartAgility.Leaves;

import com.SmartAgility.Game.CustomBank;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.regex.Pattern;

public class WithdrawPotions extends LeafTask {

    private final SmartAgility bot;

    public WithdrawPotions() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.guiData.currentTask = "Withdrawing potions.";


        if (!Bank.isOpen()) {
            Bank.open();
        } else {
            int numVials = Inventory.newQuery().names("Vial").results().size();

            if (numVials > 0) {
                Bank.deposit("Vial", numVials);
                return;
            }

            Pattern potionPattern = Pattern.compile(bot.guiData.PotionType + ".*");

            SpriteItem potion = Bank.newQuery().names(potionPattern).results().random();

            if (potion != null)
                Bank.withdraw(potion, bot.guiData.PotionAmount);
            else
                CustomBank.HasPotion = false;
        }
    }
}