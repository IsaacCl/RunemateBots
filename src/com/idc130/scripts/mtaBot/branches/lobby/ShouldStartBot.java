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
        BranchTask lobby = null;

        switch (MTABot.minigame) {
            case "graveyard":
                lobby = new IsInLobbyGraveyard();
                break;
            case "alchemist":
                lobby = new IsInLobbyAlchemist();
                break;
            case "enchantment":
                lobby = new IsInLobbyEnchantment();
                break;
            case "telekinetic":
                lobby = new IsInLobbyTelekinetic();
                break;
        }

        return new ShouldWalkToLobby(lobby);
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
