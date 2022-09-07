package com.SmartAgility.Leaves;

import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TakeABreak extends LeafTask {

    private final SmartAgility bot;

    public TakeABreak() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public void execute() {
        bot.getLogger().debug("Taking a break.");
        bot.fatigueHandler.takeBreak();
    }
}
