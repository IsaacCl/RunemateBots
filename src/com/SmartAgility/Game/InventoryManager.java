package com.SmartAgility.Game;

import com.SmartAgility.Helpers.CustomPlayerSense;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;

import java.util.regex.Pattern;

public class InventoryManager {

    final SmartAgility bot;
    private final Pattern potionPattern = Pattern.compile("(Super energy|Energy potion|Stamina potion).*");

    public InventoryManager(SmartAgility bot) {
        this.bot = bot;
    }

    public boolean hasEnergyDrinks() {
        return Inventory.newQuery().names(potionPattern).results().size() > 0;
    }

    public void drinkEnergyDrinks() {
        SpriteItem energyDrink;

        switch (CustomPlayerSense.Key.INVENTORY_SELECTOR.getAsInteger()) {
            case 1:
                energyDrink = Inventory.newQuery().names(potionPattern).results().random();
                break;
            case 2:
                energyDrink = Inventory.newQuery().names(potionPattern).results().last();
                break;
            default:
                energyDrink = Inventory.newQuery().names(potionPattern).results().first();
                break;
        }

        if (energyDrink != null && bot.guiData.drinkPotions) {
            if (energyDrink.interact("Drink")) {
                Execution.delayUntil(() -> !energyDrink.isValid(), 200, 1000);
            }
        }
    }

    public boolean needEnergyDrinks() {
        Pattern bankPotionPattern = Pattern.compile(bot.guiData.PotionType + ".*");
        return bot.guiData.PotionAmount > 0 && Inventory.newQuery().names(bankPotionPattern).results().size() == 0;
    }

    public boolean needFood() {
        return bot.guiData.FoodAmount > 0 && Inventory.newQuery().names(bot.guiData.FoodType).actions("Eat").results().size() == 0;
    }

    public void eatFood() {

        SpriteItem food;
        switch (CustomPlayerSense.Key.INVENTORY_SELECTOR.getAsInteger()) {
            case 1:
                food = Inventory.newQuery().actions("Eat").results().random();
                break;
            case 2:
                food = Inventory.newQuery().actions("Eat").results().last();
                break;
            default:
                food = Inventory.newQuery().actions("Eat").results().first();
                break;
        }

        if (food != null && bot.guiData.eatFood) {
            if (food.interact("Eat")) {
                bot.guiData.randomiseNextFoodHealth();
                Execution.delayUntil(() -> !food.isValid(), 200, 1000);
            }
        } else {
            var bot = Environment.getBot();
            if (bot != null) {
                bot.stop("Health low and no food in inventory.");
            }
        }
    }


    public void teleportSeers(Area teleportArea) {
        SpriteItem teleport = Inventory.getItems("Camelot teleport").first();

        if (teleport != null) {
            if (teleport.interact("Break")) {
                Execution.delayUntil(() -> !teleportArea.contains(Players.getLocal()), 8000);
            }
        }
    }


}
