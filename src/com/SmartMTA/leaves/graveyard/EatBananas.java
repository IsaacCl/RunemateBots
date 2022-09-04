package com.SmartMTA.leaves.graveyard;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EatBananas extends LeafTask {
    @Override
    public void execute() {
        var bananas = Inventory.getItems("Banana", "Peach");
        var numberOfBananas = bananas.size();

        if (numberOfBananas > 0) {
            Environment.getLogger().info("Eating banana/peach");
            if (bananas.get(0).click()) {
                Execution.delayUntil(() -> Inventory.getItems("Banana", "Peach").size() < numberOfBananas, 100, 1000);
            }
        } else {
            Environment.getLogger().info("Can't find banana/peach");
        }
    }
}
