package com.idc130.scripts.mtaBot.leaves.enchantment;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastEnchant extends LeafTask {
    @Override
    public void execute() {
        System.out.println("Enchanting item");

        if (Magic.LVL_4_ENCHANT.activate()) {
            Execution.delayUntil(() -> ControlPanelTab.getOpened() == ControlPanelTab.INVENTORY, 250, 1000);

            var itemToEnchant = Inventory.getItems("Cube").last();

            if (itemToEnchant != null) {
                var previousNumberCubes = Inventory.getItems("Cube").size();

                itemToEnchant.click();

                Execution.delayUntil(() -> Inventory.getItems("Cube").size() < previousNumberCubes, 250, 1000);
            }
        }
    }
}
