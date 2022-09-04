package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TeleportSeers extends LeafTask {
    private Area teleportArea = new Area.Rectangular(new Coordinate(2704, 3465, 0), new Coordinate(2708, 3459, 0));

    private SmartAgility bot;

    public TeleportSeers() {
        bot = (SmartAgility) Environment.getBot();
    }
    
    @Override
    public void execute() {
        if (Magic.CAMELOT_TELEPORT.activate("Cast")) {
            Execution.delayUntil(() -> !teleportArea.contains(Players.getLocal()), 8000);
            return;
        }

        bot.inventoryManager.teleportSeers(teleportArea);
    }
}
