package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.TeleportSeers;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CanTeleport extends BranchTask {

    private final SmartAgility bot;
    private final Area teleportArea = new Area.Rectangular(new Coordinate(2704, 3465, 0), new Coordinate(2708, 3459, 0));
    private final HealthBranch healthBranch = new HealthBranch();
    private final TeleportSeers teleportSeers = new TeleportSeers();

    public CanTeleport() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return bot.guiData.UseSeersTeleport && teleportArea.contains(Players.getLocal());
    }

    @Override
    public TreeTask failureTask() {
        return healthBranch;
    }

    @Override
    public TreeTask successTask() {
        return teleportSeers;
    }
}