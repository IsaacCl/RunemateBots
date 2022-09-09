package com.SharedLibrary.SharedLeaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ClickItem extends LeafTask {

    private final String name;

    public ClickItem(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        var item = Inventory.getItems(name).first();
        if (item != null) {
            item.click();
        }
    }
}
