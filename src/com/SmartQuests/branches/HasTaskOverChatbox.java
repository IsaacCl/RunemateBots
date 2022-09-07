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
        var components = Interfaces.newQuery().ids(17235969).textContains(" ").results();

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
                new ChatboxTask((text) -> text.contains("It's time to meet your first instructor") || text.contains("Talk to the survival expert") || text.contains("Speak to the survival expert to continue"), "Talk to survival expert"),
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
                new ChatboxTask((text) -> text.startsWith("Making dough"), "Click on flower, then click on water"),
                new ChatboxTask((text) -> text.contains("You've baked your first loaf of bread") || text.contains("Talk to the quest guide"), "Talk to quest guide"),
                new ChatboxTask((text) -> text.contains("open your quest journal"), "Open journal tab"), //Not sure about this one, fell asleep a bit
                new ChatboxTask((text) -> text.contains("It's time to enter some caves") || text.contains("Next let's get you a weapon") || text.contains("Speak to the mining instructor"), "Talk to mining instructor"),
                new ChatboxTask((text) -> text.contains("First up, try mining some tin"), "Mine some nearby tin"),
                new ChatboxTask((text) -> text.contains("You just need some copper"), "Mine some nearby copper"),
                new ChatboxTask((text) -> text.contains("You can smelt these into a bronze bar"), "Click on furnace to smelt"),
                new ChatboxTask((text) -> text.contains("click on the anvil"), "Click on the anvil"),
                new ChatboxTask((text) -> text.contains("select the dagger to continue"), "select the dagger from anvil menu"),
                new ChatboxTask((text) -> text.contains("Congratulations, you've made your first weapon") || text.contains("Well done, you've made your first kill") || text.contains("Speak to the combat instructor"), "Talk to combat instructor"),
                new ChatboxTask((text) -> text.contains("Click on the flashing icon of a man"), "Open Worn Equipment"),
                new ChatboxTask((text) -> text.contains("This is your worn inventory"), "View equipment stats"),
                new ChatboxTask((text) -> text.contains("Click on your dagger to equip it"), "Click on your dagger in inventory to equip it"),
                new ChatboxTask((text) -> text.contains("sword and shield"), "Equip sword and shield"),
                new ChatboxTask((text) -> text.contains("open the combat interface"), "Open Combat Options"),
                new ChatboxTask((text) -> text.contains("Click on the gates to continue"), "Click on nearby gate"),
                new ChatboxTask((text) -> text.contains("It's time to slay some rats"), "Attack rats"),
                new ChatboxTask((text) -> text.contains("No you have a bow and some arrows"), "Equip bow and arrow then attack rat"),
                new ChatboxTask((text) -> text.contains("If you need to go over any of what you learnt here, just talk to the combat"), "Use bank booth"),
                new ChatboxTask((text) -> text.contains("To continue, close the bank"), "Use poll booth"),
                new ChatboxTask((text) -> text.contains("Polls are run periodically") || text.contains("Talk to the Account Guide"), "Talk to account guide"),
                new ChatboxTask((text) -> text.contains("Account Management menu"), "Open Account Management"),
                new ChatboxTask((text) -> text.contains("Continue moving through the next door") || text.contains("Follow the path to the chapel") || text.contains("with Brother Brace"), "Talk to Brother Brace"),
                new ChatboxTask((text) -> text.contains("open the Prayer menu"), "open the Prayer menu"),
                new ChatboxTask((text) -> text.contains("open your friends"), "Open the Friends List"),
                new ChatboxTask((text) -> text.contains("leading to your final instructor") || text.contains("Follow the path to the wizard's house") || text.contains("This is your magic interface") || text.contains("he'll teleport you to Lumbridge"), "Talk to Magic Instructor"),
                new ChatboxTask((text) -> text.contains("Open up the magic interface"), "Open up the magic interface"),
                new ChatboxTask((text) -> text.contains("Magic casting"), "Cast wind strike")
        ));

        var text = interfaceComponent.getText();
        System.out.println(text);
        if (text != null) {
            for (var task : allChatboxTasks) {
                var todo = task.getTask(text);
                if (todo != null) {
                    System.out.println(todo);
                    return new WaitABit();
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
