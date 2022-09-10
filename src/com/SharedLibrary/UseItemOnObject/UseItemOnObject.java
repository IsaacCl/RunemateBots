package com.SharedLibrary.UseItemOnObject;

import com.SharedLibrary.InteractObject.AtCorrectArea;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SharedLibrary.SharedLeaves.SelectItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class UseItemOnObject extends BranchTask {
    private final String itemName;
    private final SmartObject object;

    public UseItemOnObject(String itemName, SmartObject object) {
        this.itemName = itemName;
        this.object = object;
    }

    @Override
    public boolean validate() {
        var selectedItem = Inventory.getSelectedItem();
        return selectedItem != null && selectedItem.getDefinition() != null && selectedItem.getDefinition().getName().equals(itemName);
    }

    @Override
    public TreeTask successTask() {
        return new AtCorrectArea(object);
    }

    @Override
    public TreeTask failureTask() {
        return new SelectItem(itemName);
    }
}
