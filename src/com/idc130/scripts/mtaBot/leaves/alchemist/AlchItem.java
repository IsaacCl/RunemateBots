package com.idc130.scripts.mtaBot.leaves.alchemist;

import com.idc130.scripts.mtaBot.state.AlchemistGameState;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class AlchItem extends LeafTask {
    @Override
    public void execute() {
        System.out.println("Alching item");

        if (Magic.HIGH_LEVEL_ALCHEMY.activate()) {
            Execution.delayUntil(() -> ControlPanelTab.getOpened() == ControlPanelTab.INVENTORY, 250, 1000);

            var itemToAlch = Inventory.getItems(AlchemistGameState.getBestItem()).first();

            if (itemToAlch != null) {
                var previousEmptySlots = Inventory.getEmptySlots();

                itemToAlch.click();

                Execution.delayUntil(() -> Inventory.getEmptySlots() > previousEmptySlots, 250, 1000);
            }
        }
    }
}