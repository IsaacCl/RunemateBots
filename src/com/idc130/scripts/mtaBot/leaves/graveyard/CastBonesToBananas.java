package com.idc130.scripts.mtaBot.leaves.graveyard;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastBonesToBananas extends LeafTask {
    @Override
    public void execute() {
        Environment.getLogger().info("Casting bones to banana");
        if (Magic.BONES_TO_BANANAS.activate()) {
            Execution.delayUntil(() -> !Inventory.contains("Banana"), 100, 1000);
        }
    }
}
