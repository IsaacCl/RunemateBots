package com.idc130.scripts.mtaBot.leaves.telekinetic;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TeleportTelekinetic extends LeafTask {
    @Override
    public void execute() {
        var alchemistsTeleport = GameObjects.newQuery().names("Telekinetic Teleport").results().nearest();
        if (alchemistsTeleport != null) {
            Environment.getLogger().info("Going to telekinetic");
            alchemistsTeleport.click();
        } else {
            Environment.getLogger().info("Can't go to telekinetic");
        }
    }
}
