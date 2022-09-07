package com.SmartAgility.Branches;

import com.SmartAgility.Leaves.PickupItems;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class PickupBranch extends BranchTask {

    private final PickupItems pickupItems = new PickupItems();
    private final ObstacleBranch obstacleBranch = new ObstacleBranch();

    private final SmartAgility bot;

    public PickupBranch() {
        bot = (SmartAgility) Environment.getBot();
    }

    @Override
    public boolean validate() {
        return bot.groundManager.checkGroundAt(bot);
    }

    @Override
    public TreeTask successTask() {
        return pickupItems;
    }

    @Override
    public TreeTask failureTask() {
        return obstacleBranch;
    }
}
