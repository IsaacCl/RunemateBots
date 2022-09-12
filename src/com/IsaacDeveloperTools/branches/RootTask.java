package com.IsaacDeveloperTools.branches;

import com.IsaacDeveloperTools.IsaacDeveloperTools;
import com.runemate.game.api.hybrid.entities.details.Onymous;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.stream.Collectors;

public class RootTask extends BranchTask {

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask successTask() {
        return null;
    }


    @Override
    public TreeTask failureTask() {
        return new LeafTask() {
            @Override
            public void execute() {
                IsaacDeveloperTools.nearbyGameObjectNames = GameObjects.newQuery().results().stream().map(obj -> {
                    if (obj.getDefinition() != null) return obj.getDefinition().getName();
                    else return "";
                }).collect(Collectors.toList());
                IsaacDeveloperTools.nearbyNpcNames = Npcs.newQuery().results().stream().map(Onymous::getName).collect(Collectors.toList());
                IsaacDeveloperTools.currentPlayerPosition = Players.getLocal().getPosition();

                Execution.delay(100);
            }
        };
    }
}
