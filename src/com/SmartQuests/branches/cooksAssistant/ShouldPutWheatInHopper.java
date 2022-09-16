package com.SmartQuests.branches.cooksAssistant;

import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Chatbox;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldPutWheatInHopper extends BranchTask {
    @Override
    public boolean validate() {
        var latestChat = Chatbox.newQuery().results().last();

        if (latestChat != null)
            System.out.println(latestChat.getMessage());

        return Inventory.contains("Grain") && (latestChat == null || !latestChat.getMessage().contains("You put the grain in the hopper.") && !latestChat.getMessage().contains("There is already grain in the hopper."));
    }

    @Override
    public TreeTask successTask() {
        return new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3166, 3308, 2), new Coordinate(3167, 3308, 2)), "Hopper", "GameObject"));
    }

    @Override
    public TreeTask failureTask() {
        return new IsProcessingWheat();
    }
}
