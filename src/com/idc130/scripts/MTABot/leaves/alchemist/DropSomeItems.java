package com.idc130.scripts.MTABot.leaves.alchemist;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

import static com.idc130.scripts.MTABot.state.AlchemistGameState.getItemsArray;

public class DropSomeItems extends LeafTask {
    @Override
    public void execute() {
        var item = Inventory.getItems(getItemsArray()).first();

        if(item != null)
        {
            item.interact("Drop");
        }
    }
}
