package com.SmartMTA.leaves.alchemist;

import com.SmartMTA.MTABot;
import com.SmartMTA.state.AlchemistGameState;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class AlchItem extends LeafTask {
    @Override
    public void execute() {
        Environment.getLogger().info("Alching item");

        var spell = MTABot.alchemySpell.equals("High alchemy") ? Magic.HIGH_LEVEL_ALCHEMY : Magic.LOW_LEVEL_ALCHEMY;

        if (spell.activate()) {
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
