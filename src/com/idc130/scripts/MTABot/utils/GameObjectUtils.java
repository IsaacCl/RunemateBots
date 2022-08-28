package com.idc130.scripts.MTABot.utils;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;

public class GameObjectUtils {
    public static void pickUpNearbyItem(String itemName)
    {
        var item = GameObjects.newQuery().names(itemName).results().nearest();

        if(item != null)
        {
            var previousEmptySlots = Inventory.getEmptySlots();
            item.click();
            Execution.delayUntil(() -> Inventory.getEmptySlots() < previousEmptySlots, 250, 1000);
        }
    }
}
