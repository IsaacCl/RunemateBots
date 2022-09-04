package com.SmartMTA.leaves.telekinetic;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastTelekineticGrab extends LeafTask {
    @Override
    public void execute() {
        Environment.getLogger().info("Cast telekinetic grab");

        if (Magic.TELEKINETIC_GRAB.isSelected() || Magic.TELEKINETIC_GRAB.activate()) {
            Execution.delay(250, 500);

            var mazeGuardian = Npcs.newQuery().names("Maze Guardian").results().first();

            if (mazeGuardian != null) {
                mazeGuardian.click();
                Execution.delayUntil(() -> {
                    var mazeGuardian2 = Npcs.newQuery().names("Maze Guardian").results().first();
                    return mazeGuardian2 == null || mazeGuardian2.isMoving();
                }, 1000, 4000);
            }
        }
    }
}
