package com.SmartQuests.branches;

import com.SmartQuests.leaves.WaitABit;
import com.SmartQuests.utils.ChatboxTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.ArrayList;
import java.util.Arrays;

public class HasTaskOverChatbox extends BranchTask {

    private InterfaceComponent interfaceComponent;

    @Override
    public boolean validate() {
        // Could also query: text starts with
        var components = Interfaces.newQuery().ids(17235969).textContains("Getting started",
                "Settings menu", "Cooking", "You've been given an item", "Moving along", "Firemaking", "Fishing",
                "Woodcutting", "Please wait", "Making dough").results();

        if (components.size() > 0) {
            interfaceComponent = components.get(0);
            return true;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        var allChatboxTasks = new ArrayList<>(Arrays.asList(
                new ChatboxTask((text) -> text.contains("Talk to the Gielinor Guide to continue.") || text.startsWith("Getting started"), "Talk to Gielinor Guide"),
                new ChatboxTask((text) -> text.startsWith("Settings menu"), "Open settings menu"),
                new ChatboxTask((text) -> text.contains("Talk to the survival expert") || text.contains("Speak to the survival expert to continue"), "Talk to survival expert"),
                new ChatboxTask((text) -> text.startsWith("You've been given an item"), "Open inventory"),
                new ChatboxTask((text) -> text.startsWith("Fishing"), "Net fishing spot"),
                new ChatboxTask((text) -> text.startsWith("You've gained some experience"), "Open Skill tab"),
                new ChatboxTask((text) -> text.startsWith("Woodcutting"), "Cut tree"),
                new ChatboxTask((text) -> text.startsWith("Please wait"), "Do nothing"),
                new ChatboxTask((text) -> text.startsWith("Firemaking"), "Select tinderbox then select logs"),
                new ChatboxTask((text) -> text.contains("Click on the gate shown and follow the path") ||
                        text.contains("Follow the path until you get to the door with the yellow arrow") ||
                        text.startsWith("Cooking") && text.contains("Talk to the chef indicated"), "Talk to master chef"),
                new ChatboxTask((text) -> text.startsWith("Cooking") && text.contains("shrimp"), "Select shrimp then select fire"),
                new ChatboxTask((text) -> text.startsWith("Making dough"), "Click on flower, then click on water")
        ));
        
        var text = interfaceComponent.getText();
        System.out.println(text);
        if (text != null) {
            for (var task : allChatboxTasks) {
                var todo = task.getTask(text);
                if (todo != null) {
                    System.out.println(todo);
                }
            }
        }

        return new WaitABit();
    }

    @Override
    public TreeTask failureTask() {
        System.out.println("Wtf");
        return new WaitABit();
    }
}
