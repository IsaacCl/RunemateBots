package com.SharedLibrary.CombineItems;

import com.SharedLibrary.SharedLeaves.ClickItem;
import com.SharedLibrary.SharedLeaves.SelectItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CombineItems extends BranchTask {

    private String item1;
    private String item2;

    public CombineItems(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public boolean validate() {
        var selectedItem = Inventory.getSelectedItem();
        return selectedItem != null && selectedItem.getDefinition().getName().equals(item1);
    }

    @Override
    public TreeTask successTask() {
        return new ClickItem(item2);
    }

    @Override
    public TreeTask failureTask() {
        return new SelectItem(item1);
    }
}
