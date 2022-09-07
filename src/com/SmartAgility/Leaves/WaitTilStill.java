package com.SmartAgility.Leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WaitTilStill extends LeafTask {
    @Override
    public void execute() {
        Player localPlayer = Players.getLocal();
        if (localPlayer != null) {
            Execution.delayUntil(() -> !localPlayer.isMoving(), 2000);
        }
    }
}
