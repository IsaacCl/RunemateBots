package com.SmartMTA.leaves.alchemist;

import com.SmartMTA.state.AlchemistGameState;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GoToCupboard extends LeafTask {
    @Override
    public void execute() {
        var location = AlchemistGameState.getBestCupboardArea();

        final RegionPath path = RegionPath.buildTo(location);
        var me = Players.getLocal();
        if (path != null && me != null) {
            Environment.getLogger().info("Walking to cupboard " + AlchemistGameState.getBestItemLocationIndex());
            path.step(true);
            Execution.delayUntil(() -> me.getAnimationId() != -1, 500, 2000);
        } else {
            Environment.getLogger().info("Couldn't build a path to cupboard");
        }
    }
}
