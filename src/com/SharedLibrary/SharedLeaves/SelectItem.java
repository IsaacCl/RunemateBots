package com.SharedLibrary.SharedLeaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SelectItem extends LeafTask {

    private final String itemName;

    public SelectItem(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        var item = Inventory.getItems(itemName).first();
        if (item != null) {
            item.click();
        }
    }
}
