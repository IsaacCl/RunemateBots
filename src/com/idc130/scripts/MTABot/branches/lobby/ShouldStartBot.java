package com.idc130.scripts.MTABot.branches.lobby;

import com.idc130.scripts.MTABot.MTABot;
import com.idc130.scripts.MTABot.branches.alchemist.IsInLobbyAlchemist;
import com.idc130.scripts.MTABot.branches.enchantment.IsInLobbyEnchantment;
import com.idc130.scripts.MTABot.branches.graveyard.IsInLobbyGraveyard;
import com.idc130.scripts.MTABot.branches.telekinetic.IsInLobbyTelekinetic;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldStartBot extends BranchTask {
    @Override
    public boolean validate() {
        return MTABot.shouldDoCreatureGraveyard || MTABot.shouldDoAlchemist || MTABot.shouldDoEnchanters || MTABot.shouldToTelekinetic;
    }

    @Override
    public TreeTask successTask() {
        if (MTABot.shouldDoCreatureGraveyard) {
            return new IsInLobbyGraveyard();
        } else if (MTABot.shouldDoAlchemist) {
            return new IsInLobbyAlchemist();
        } else if (MTABot.shouldDoEnchanters) {
            return new IsInLobbyEnchantment();
        } else if (MTABot.shouldToTelekinetic) {
            return new IsInLobbyTelekinetic();
        }

        return null;
    }

    @Override
    public TreeTask failureTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                System.out.println("Doing nothing!");
            }
        };
    }
}
