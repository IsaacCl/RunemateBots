package com.SmartQuests.branches;

import com.SharedLibrary.CombineItems.CombineItems;
import com.SharedLibrary.InteractObject.InteractObject;
import com.SharedLibrary.InteractObject.SmartObject;
import com.SharedLibrary.InteractObject.WaitingCondition;
import com.SharedLibrary.SharedLeaves.ClickItem;
import com.SharedLibrary.SharedLeaves.ClickOnInterface;
import com.SharedLibrary.SharedLeaves.DoNothing;
import com.SharedLibrary.SharedLeaves.OpenInterface;
import com.SharedLibrary.UseItemOnObject.UseItemOnObject;
import com.SharedLibrary.UseMagicOnObject.UseMagicOnObject;
import com.SmartQuests.utils.ChatboxTask;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
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
                new ChatboxTask((text) -> text.startsWith("Please wait") || text.startsWith("Sit back and watch"), new DoNothing("Do nothing")),
                new ChatboxTask((text) -> text.startsWith("Firemaking"), new CombineItems("Tinderbox", "Logs")),
                new ChatboxTask((text) -> text.contains("Click on the gate shown and follow the path") ||
                        text.contains("Follow the path until you get to the door with the yellow arrow") ||
                        text.startsWith("Cooking") && text.contains("Talk to the chef indicated"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3075, 3084, 0), new Coordinate(3077, 3085, 0)), "Master Chef", "Npc"))),
                new ChatboxTask((text) -> text.startsWith("Cooking") && text.contains("shrimp"), new UseItemOnObject("Raw shrimps", new SmartObject(new Area.Rectangular(new Coordinate(3104, 3095, 0), new Coordinate(3101, 3098, 0)), "Fire", "GameObject"))),
                new ChatboxTask((text) -> text.startsWith("Making dough"), new CombineItems("Pot of flour", "Bucket of water")),
                new ChatboxTask((text) -> text.startsWith("Cooking dough"), new UseItemOnObject("Bread dough", new SmartObject(new Area.Rectangular(new Coordinate(3075, 3082, 0), new Coordinate(3076, 3082, 0)), "Range", "GameObject"))),
                new ChatboxTask((text) -> text.contains("You've baked your first loaf of bread") || text.toLowerCase().contains("to the quest guide") || text.startsWith("Fancy a run?"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3087, 3122, 0), new Coordinate(3085, 3123, 0)), "Quest Guide", "Npc"))),
                new ChatboxTask((text) -> text.startsWith("Quest journal"), new OpenInterface(ControlPanelTab.QUEST_LIST)),
                new ChatboxTask((text) -> text.contains("It's time to enter some caves") || text.contains("Next let's get you a weapon") || (text.contains("Speak to the mining instructor") && !text.contains("for a recap at any time")), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3080, 9502, 0), new Coordinate(3079, 9505, 0)), "Mining Instructor", "Npc"))),
                new ChatboxTask((text) -> text.contains("First up, try mining some tin"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3078, 9504, 0), new Coordinate(3078, 9503, 0)), 10080, "GameObject"))),
                new ChatboxTask((text) -> text.contains("you just need some copper"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3087, 9504, 0), new Coordinate(3085, 9504, 0)), 10079, "GameObject"))),
                new ChatboxTask((text) -> text.contains("You can smelt these into a bronze bar"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3080, 9498, 0), new Coordinate(3078, 9498, 0)), "Furnace", "GameObject"))),
                new ChatboxTask((text) -> text.contains("click on the anvil") || (text.contains("select the dagger to continue") && Interfaces.newQuery().textContains("Dagger").results().size() == 0), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3082, 9498, 0), new Coordinate(3082, 9500, 0)), "Anvil", "GameObject", new WaitingCondition(() -> Interfaces.newQuery().textContains("Dagger").results().size() > 0, 1000, 2000)))),
                new ChatboxTask((text) -> text.contains("select the dagger to continue"), new ClickOnInterface("Dagger", () -> Inventory.contains("Bronze dagger"))),
                new ChatboxTask((text) -> text.contains("Congratulations, you've made your first weapon") || text.contains("melee and ranged combat") || text.contains("Well done, you've made your first kill") || text.contains("Speak to the combat instructor"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3104, 9509, 0), new Coordinate(3107, 9508, 0)), "Combat Instructor", "Npc"))),
                new ChatboxTask((text) -> text.contains("Click on the flashing icon of a man") || (text.contains("This is your worn inventory") && Interfaces.newQuery().ids(25362433).results().size() == 0), new OpenInterface(ControlPanelTab.EQUIPMENT)),
                new ChatboxTask((text) -> text.contains("This is your worn inventory"), new ClickOnInterface(25362433)),
                new ChatboxTask((text) -> text.contains("Click your dagger to equip it"), new ClickItem("Bronze dagger")),
                new ChatboxTask((text) -> text.contains("sword and shield"), new ClickTwoItems("Bronze sword", "Wooden shield")),
                new ChatboxTask((text) -> text.contains("open the combat interface"), new OpenInterface(ControlPanelTab.COMBAT_OPTIONS)),
                new ChatboxTask((text) -> text.contains("It's time to slay some rats") || text.contains("Click on the gates to continue"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3105, 9517, 0), new Coordinate(3109, 9520, 0)), "Giant rat", "Npc"))),
                new ChatboxTask((text) -> text.contains("No you have a bow and some arrows"), new ClickTwoItems("Shortbow", "Bronze arrow")),
                new ChatboxTask((text) -> text.startsWith("Rat ranging"), new RangeRat()),
                new ChatboxTask((text) -> text.contains("If you need to go over any of what you learnt here, just talk to the combat") || text.contains("This is the Bank of Gielinor"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3120, 3123, 0), new Coordinate(3122, 3123, 0)), "Bank booth", "GameObject"))),
                new ChatboxTask((text) -> text.contains("To continue, close the bank"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3120, 3120, 0), new Coordinate(3120, 3122, 0)), "Poll booth", "GameObject"))),
                new ChatboxTask((text) -> text.contains("Polls are run periodically") || text.contains("Talk to the Account Guide") || text.contains("The guide here will tell you all about your account"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3125, 3124, 0), new Coordinate(3127, 3124, 0)), "Account Guide", "Npc"))),
                new ChatboxTask((text) -> text.contains("Account Management menu"), new OpenInterface(ControlPanelTab.ACCOUNT_MANAGEMENT)),
                new ChatboxTask((text) -> text.contains("Continue through the next door.") || text.contains("Continue moving through the next door") || text.contains("Follow the path to the chapel") || text.contains("with Brother Brace"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3126, 3106, 0), new Coordinate(3123, 3107, 0)), "Brother Brace", "Npc"))),
                new ChatboxTask((text) -> text.contains("open the Prayer menu"), new OpenInterface(ControlPanelTab.PRAYER)),
                new ChatboxTask((text) -> text.contains("open your friends"), new OpenInterface(ControlPanelTab.FRIENDS_LIST)),
                new ChatboxTask((text) -> text.contains("leading to your final instructor") || text.contains("Follow the path to the wizard's house") || text.contains("This is your magic interface") || text.contains("he'll teleport you to Lumbridge"), new InteractObject(new SmartObject(new Area.Rectangular(new Coordinate(3140, 3088, 0), new Coordinate(3141, 3090, 0)), "Magic Instructor", "Npc"))),
                new ChatboxTask((text) -> text.contains("Open up the magic interface"), new OpenInterface(ControlPanelTab.MAGIC)),
                new ChatboxTask((text) -> text.contains("Magic casting"), new UseMagicOnObject(Magic.WIND_STRIKE, new SmartObject(new Area.Rectangular(new Coordinate(3138, 3091, 0), new Coordinate(3140, 3091, 0)), "Chicken", "Npc")))
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

        return new DoNothing("Nothing to do");
    }

    @Override
    public TreeTask failureTask() {
        return new TutorialIslandFinished();
    }
}
