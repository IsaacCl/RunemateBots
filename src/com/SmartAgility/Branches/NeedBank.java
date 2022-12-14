package com.SmartAgility.Branches;

import com.SmartAgility.Game.CustomBank;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class NeedBank extends BranchTask {

    private final SmartAgility bot;
    private final AtBank atBank = new AtBank();
    private final CanTeleport canTeleport = new CanTeleport();
    private boolean banking = false;

    public NeedBank() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        if (bot.courseList.getCurrentCourse().hasBank()) {
            return (banking || bot.courseList.isPlayerInFirstArea() || bot.courseList.getCurrentCourse().getBank().atBank())
                    && ((bot.inventoryManager.needEnergyDrinks() && CustomBank.HasPotion)
                    || (bot.inventoryManager.needFood() && CustomBank.HasFood));
        } else {
            return false;
        }
    }

    @Override
    public TreeTask failureTask() {
        banking = false;
        return canTeleport;
    }

    @Override
    public TreeTask successTask() {
        banking = true;
        return atBank;
    }
}