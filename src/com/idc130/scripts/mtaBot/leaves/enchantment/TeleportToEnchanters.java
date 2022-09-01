package com.idc130.scripts.mtaBot.leaves.enchantment;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TeleportToEnchanters extends LeafTask {
    @Override
    public void execute() {
        var alchemistsTeleport = GameObjects.newQuery().names("Enchanters Teleport").results().nearest();
        if (alchemistsTeleport != null) {
            Environment.getLogger().info("Going to enchanters");
            alchemistsTeleport.click();
        } else {
            Environment.getLogger().info("Can't go to enchanters");
        }
    }
}
