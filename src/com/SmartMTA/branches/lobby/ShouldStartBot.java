package com.SmartMTA.branches.lobby;

import com.SmartMTA.MTABot;
import com.SmartMTA.branches.alchemist.IsInLobbyAlchemist;
import com.SmartMTA.branches.enchantment.IsInLobbyEnchantment;
import com.SmartMTA.branches.graveyard.IsInLobbyGraveyard;
import com.SmartMTA.branches.telekinetic.IsInLobbyTelekinetic;
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
            case "Creature Graveyard":
                lobby = new IsInLobbyGraveyard();
                break;
            case "Alchemists' Playground":
                lobby = new IsInLobbyAlchemist();
                break;
            case "Enchanting Chamber":
                lobby = new IsInLobbyEnchantment();
                break;
            case "Telekinetic Theatre":
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
