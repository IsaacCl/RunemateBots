package com.idc130.scripts.MTABot.branches;

import com.idc130.scripts.MTABot.MTABot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldDoCreatureGraveyard extends BranchTask {
    @Override
    public boolean validate() {
        return MTABot.shouldDoCreatureGraveyard;
    }

    @Override
    public TreeTask successTask() {
        return new IsInLobby();
    }

    @Override
    public TreeTask failureTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                System.out.println("Currently not doing creature graveyard.");
            }
        };
    }
}
