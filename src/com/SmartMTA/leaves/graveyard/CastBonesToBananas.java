package com.SmartMTA.leaves.graveyard;

import com.SmartMTA.MTABot;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastBonesToBananas extends LeafTask {
    @Override
    public void execute() {
        Environment.getLogger().info("Casting bones to banana/peaches");
        Magic spell;
        if (MTABot.graveyardSpell.equals("Bones to peaches")) {
            spell = Magic.BONES_TO_PEACHES;
        } else {
            spell = Magic.BONES_TO_BANANAS;
        }
        if (spell.activate()) {
            Execution.delayUntil(() -> Inventory.getItems("Banana", "Peach").size() > 0, 100, 1000);
        }
    }
}
