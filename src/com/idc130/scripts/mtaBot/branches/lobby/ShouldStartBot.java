package com.idc130.scripts.mtaBot.branches.lobby;

import com.idc130.scripts.mtaBot.MTABot;
import com.idc130.scripts.mtaBot.branches.alchemist.IsInLobbyAlchemist;
import com.idc130.scripts.mtaBot.branches.enchantment.IsInLobbyEnchantment;
import com.idc130.scripts.mtaBot.branches.graveyard.IsInLobbyGraveyard;
import com.idc130.scripts.mtaBot.branches.telekinetic.IsInLobbyTelekinetic;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.Objects;

public class ShouldStartBot extends BranchTask {
    @Override
    public boolean validate() {
        return !Objects.equals(MTABot.minigame, "");
    }

    @Override
    public TreeTask successTask() {
        switch (MTABot.minigame) {
            case "graveyard":
                return new IsInLobbyGraveyard();
            case "alchemist":
                return new IsInLobbyAlchemist();
            case "enchantment":
                return new IsInLobbyEnchantment();
            case "telekinetic":
                return new IsInLobbyTelekinetic();
        }

        return null;
    }

    @Override
    public TreeTask failureTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                Environment.getLogger().info("Doing nothing!");
            }
        };
    }
}
