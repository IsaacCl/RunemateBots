package com.SmartQuests.branches;

import com.SharedLibrary.SharedLeaves.ClickOnInterface;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CoveredByInterfaces extends BranchTask {
    @Override
    public boolean validate() {
        var numberPeskyInterfaces = Interfaces.newQuery().ids(5505027).results().size() + Interfaces.newQuery().textContains("Click here to continue").results().size();
        return numberPeskyInterfaces > 0;
    }

    @Override
    public TreeTask successTask() {
        if (Interfaces.newQuery().textContains("Click here to continue").results().size() > 0) {
            return new ClickOnInterface("Click here to continue");
        } else {
            return new LeafTask() {
                @Override
                public void execute() {
                    if (Interfaces.newQuery().ids(5505027).results().size() > 0) {
                        var me = Players.getLocal();
                        if (me != null) {
                            final RegionPath path = RegionPath.buildTo(me);
                            if (path != null) {
                                Environment.getLogger().info("Walking to me");
                                if (path.step(true)) {
                                    Execution.delayUntil(() -> me.getAnimationId() != -1, 500, 2000);
                                }
                            }
                        }
                    }
                }
            };
        }
    }

    @Override
    public TreeTask failureTask() {
        return new HasTaskOverChatbox();
    }
}
