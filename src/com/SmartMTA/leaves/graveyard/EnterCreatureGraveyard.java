package com.SmartMTA.leaves.graveyard;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EnterCreatureGraveyard extends LeafTask {
    @Override
    public void execute() {
        var graveyardTeleport = GameObjects.newQuery().names("Graveyard Teleport").results().nearest();
        if (graveyardTeleport != null) {
            Environment.getLogger().info("Going to graveyard");
            graveyardTeleport.click();
        } else {
            Environment.getLogger().info("Can't go to graveyard");
        }
    }
}
