package com.idc130.scripts.mtaBot.utils;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;

public class WalkingUtils {

    public static void walkToLocalArea(Area area, String areaName) {
        final RegionPath path = RegionPath.buildTo(area);
        var me = Players.getLocal();
        if (path != null && me != null) {
            Environment.getLogger().info("Walking to " + areaName);
            path.step(true);
            Execution.delayUntil(() -> me.getAnimationId() != -1, 500, 2000);
        } else {
            Environment.getLogger().info("Couldn't build a path to " + areaName);
        }

    }

}
