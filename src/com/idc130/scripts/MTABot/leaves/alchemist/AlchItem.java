package com.idc130.scripts.MTABot.leaves.alchemist;

import com.idc130.scripts.MTABot.state.AlchemistGameState;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class AlchItem extends LeafTask {
    @Override
    public void execute() {
        System.out.println("Alching item");

        if(Magic.HIGH_LEVEL_ALCHEMY.activate())
        {
            var itemToAlch = Inventory.getItems(AlchemistGameState.getItemsArray()).first();

            if(itemToAlch != null)
            {
                itemToAlch.click();
            }
        }
    }
}
