package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class PickupItems extends LeafTask {

    private final SmartAgility bot;

    public PickupItems() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.getLogger().debug("Pick up items.");
        bot.guiData.currentTask = "Picking up items.";

        bot.groundManager.collectItems();
    }
}
