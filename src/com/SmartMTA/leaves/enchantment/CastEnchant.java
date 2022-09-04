package com.SmartMTA.leaves.enchantment;

import com.SmartMTA.MTABot;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastEnchant extends LeafTask {
    @Override
    public void execute() {
        Environment.getLogger().info("Enchanting item");

        Magic spell;

        switch (MTABot.enchantSpell) {
            case "Enchant level 1":
                spell = Magic.LVL_1_ENCHANT;
                break;
            case "Enchant level 2":
                spell = Magic.LVL_2_ENCHANT;
                break;
            case "Enchant level 3":
                spell = Magic.LVL_3_ENCHANT;
                break;
            case "Enchant level 4":
                spell = Magic.LVL_4_ENCHANT;
                break;
            case "Enchant level 5":
                spell = Magic.LVL_5_ENCHANT;
                break;
            case "Enchant level 6":
                spell = Magic.LVL_6_ENCHANT;
                break;
            default:
                spell = Magic.LEVEL_7_ENCHANT; // This name is stupid.
        }

        if (spell.activate()) {
            Execution.delayUntil(() -> ControlPanelTab.getOpened() == ControlPanelTab.INVENTORY, 500, 2000);

            var itemToEnchant = Inventory.getItems("Cube").last();

            if (itemToEnchant != null) {
                var previousNumberCubes = Inventory.getItems("Cube").size();

                itemToEnchant.click();

                Execution.delayUntil(() -> Inventory.getItems("Cube").size() < previousNumberCubes, 500, 2000);
            }
        }
    }
}
