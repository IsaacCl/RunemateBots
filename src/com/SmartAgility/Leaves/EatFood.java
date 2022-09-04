package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatFood extends LeafTask {

    private SmartAgility bot;
    public EatFood() { bot = (SmartAgility) Environment.getBot(); }


    @Override
    public void execute() {
        bot.guiData.currentTask = "Eating some food.";
        bot.getLogger().debug("Eating some food.");
        bot.inventoryManager.eatFood();
    }
}
