package com.idc130.scripts.mtaBot.leaves.alchemist;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TeleportToAlchemist extends LeafTask {
    @Override
    public void execute() {
        var alchemistsTeleport = GameObjects.newQuery().names("Alchemists Teleport").results().nearest();
        if (alchemistsTeleport != null) {
            Environment.getLogger().info("Going to alchemist");
            alchemistsTeleport.click();
        } else {
            Environment.getLogger().info("Can't go to alchemist");
        }
    }
}
