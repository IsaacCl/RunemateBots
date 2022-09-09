package com.SmartQuests.branches;

import com.SharedLibrary.CombineItems.CombineItems;
import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SharedLibrary.SharedLeaves.DoNothing;
import com.SharedLibrary.SharedLeaves.OpenInterface;
import com.SharedLibrary.UseItemOnObject.UseItemOnObject;
import com.SmartQuests.leaves.WaitABit;
import com.SmartQuests.utils.ChatboxTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
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
                new ChatboxTask((text) -> text.contains("Talk to the Gielinor Guide to continue.") || text.startsWith("Getting started"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3092, 3106, 0), new Coordinate(3094, 3108, 0)), "Gielinor Guide", "Npc"))),
                new ChatboxTask((text) -> text.startsWith("Settings menu"), new OpenInterface(ControlPanelTab.SETTINGS)),
                new ChatboxTask((text) -> text.contains("It's time to meet your first instructor") || text.contains("Talk to the survival expert") || text.contains("Speak to the survival expert to continue"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3104, 3095, 0), new Coordinate(3102, 3097, 0)), "Survival Expert", "Npc"))),
                new ChatboxTask((text) -> text.startsWith("You've been given an item"), new OpenInterface(ControlPanelTab.INVENTORY)),
                new ChatboxTask((text) -> text.startsWith("Fishing"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3101, 3093, 0), new Coordinate(3100, 3093, 0)), "Fishing spot", "Npc"))),
                new ChatboxTask((text) -> text.startsWith("You've gained some experience"), new OpenInterface(ControlPanelTab.SKILLS)),
                new ChatboxTask((text) -> text.startsWith("Woodcutting"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3100, 3094, 0), new Coordinate(3099, 3094, 0)), "Tree", "GameObject"))),
                new ChatboxTask((text) -> text.startsWith("Please wait"), new DoNothing("Do nothing")),
                new ChatboxTask((text) -> text.startsWith("Firemaking"), new CombineItems("Tinderbox", "Logs")),
                new ChatboxTask((text) -> text.contains("Click on the gate shown and follow the path") ||
                        text.contains("Follow the path until you get to the door with the yellow arrow") ||
                        text.startsWith("Cooking") && text.contains("Talk to the chef indicated"), new DoNothing("Talk to master chef")),
                new ChatboxTask((text) -> text.startsWith("Cooking") && text.contains("shrimp"), new UseItemOnObject("Raw shrimps", new SmartObject(new Area.Rectangular(new Coordinate(3104, 3095, 0), new Coordinate(3101, 3098, 0)), "Fire", "GameObject"))),
                new ChatboxTask((text) -> text.startsWith("Making dough"), new DoNothing("Click on flour, then click on water")),
                new ChatboxTask((text) -> text.contains("You've baked your first loaf of bread") || text.contains("Talk to the quest guide"), new DoNothing("Talk to quest guide")),
                new ChatboxTask((text) -> text.contains("open your quest journal"), new OpenInterface(ControlPanelTab.QUEST_LIST)), //Not sure about this one, fell asleep a bit
                new ChatboxTask((text) -> text.contains("It's time to enter some caves") || text.contains("Next let's get you a weapon") || text.contains("Speak to the mining instructor"), new DoNothing("Talk to mining instructor")),
                new ChatboxTask((text) -> text.contains("First up, try mining some tin"), new DoNothing("Mine some nearby tin")),
                new ChatboxTask((text) -> text.contains("You just need some copper"), new DoNothing("Mine some nearby copper")),
                new ChatboxTask((text) -> text.contains("You can smelt these into a bronze bar"), new DoNothing("Click on furnace to smelt")),
                new ChatboxTask((text) -> text.contains("click on the anvil"), new DoNothing("Click on the anvil")),
                new ChatboxTask((text) -> text.contains("select the dagger to continue"), new DoNothing("select the dagger from anvil menu")),
                new ChatboxTask((text) -> text.contains("Congratulations, you've made your first weapon") || text.contains("Well done, you've made your first kill") || text.contains("Speak to the combat instructor"), new DoNothing("Talk to combat instructor")),
                new ChatboxTask((text) -> text.contains("Click on the flashing icon of a man"), new OpenInterface(ControlPanelTab.EQUIPMENT)),
                new ChatboxTask((text) -> text.contains("This is your worn inventory"), new DoNothing("View equipment stats")),
                new ChatboxTask((text) -> text.contains("Click on your dagger to equip it"), new DoNothing("Click on your dagger in inventory to equip it")),
                new ChatboxTask((text) -> text.contains("sword and shield"), new DoNothing("Equip sword and shield")),
                new ChatboxTask((text) -> text.contains("open the combat interface"), new DoNothing("Open Combat Options")),
                new ChatboxTask((text) -> text.contains("Click on the gates to continue"), new DoNothing("Click on nearby gate")),
                new ChatboxTask((text) -> text.contains("It's time to slay some rats"), new DoNothing("Attack rats")),
                new ChatboxTask((text) -> text.contains("No you have a bow and some arrows"), new DoNothing("Equip bow and arrow then attack rat")),
                new ChatboxTask((text) -> text.contains("If you need to go over any of what you learnt here, just talk to the combat"), new DoNothing("Use bank booth")),
                new ChatboxTask((text) -> text.contains("To continue, close the bank"), new DoNothing("Use poll booth")),
                new ChatboxTask((text) -> text.contains("Polls are run periodically") || text.contains("Talk to the Account Guide"), new DoNothing("Talk to account guide")),
                new ChatboxTask((text) -> text.contains("Account Management menu"), new DoNothing("Open Account Management")),
                new ChatboxTask((text) -> text.contains("Continue moving through the next door") || text.contains("Follow the path to the chapel") || text.contains("with Brother Brace"), new DoNothing("Talk to Brother Brace")),
                new ChatboxTask((text) -> text.contains("open the Prayer menu"), new OpenInterface(ControlPanelTab.PRAYER)),
                new ChatboxTask((text) -> text.contains("open your friends"), new OpenInterface(ControlPanelTab.FRIENDS_LIST)),
                new ChatboxTask((text) -> text.contains("leading to your final instructor") || text.contains("Follow the path to the wizard's house") || text.contains("This is your magic interface") || text.contains("he'll teleport you to Lumbridge"), new DoNothing("Talk to Magic Instructor")),
                new ChatboxTask((text) -> text.contains("Open up the magic interface"), new DoNothing("Open up the magic interface")),
                new ChatboxTask((text) -> text.contains("Magic casting"), new DoNothing("Cast wind strike"))
        ));

        var text = interfaceComponent.getText();
        System.out.println(text);
        if (text != null) {
            for (var task : allChatboxTasks) {
                var todo = task.getTask(text);
                if (todo != null) {
                    return todo;
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
