package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.NextObstacle;
import com.SmartAgility.Leaves.WaitTilStill;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ObstacleBranch extends BranchTask {

    private final NextObstacle nextObstacle = new NextObstacle();
    private final WaitTilStill waitTilStill = new WaitTilStill();

    private final SmartAgility bot;

    public ObstacleBranch() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return !bot.courseList.doingObstacle();
    }

    @Override
    public TreeTask successTask() {
        return nextObstacle;
    }

    @Override
    public TreeTask failureTask() {
        return waitTilStill;
    }
}
