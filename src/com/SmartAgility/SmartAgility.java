package com.SmartAgility;

import com.SmartAgility.Branches.RootBranch;
import com.SmartAgility.Game.CourseList;
import com.SmartAgility.Game.GroundManager;
import com.SmartAgility.Game.InventoryManager;
import com.SmartAgility.Game.PlayerManager;
import com.SmartAgility.Fatigue.FatigueHandler;
import com.SmartAgility.Helpers.CustomPlayerSense;
import com.SmartAgility.UI.GUIData;
import com.SmartAgility.UI.SmartAgilityFXUI;
import com.SmartAgility.UI.SmartAgilityInfoUI;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.Timer;
import java.util.TimerTask;

public class SmartAgility extends TreeBot implements EmbeddableUI, InventoryListener, SkillListener {

    public CourseList courseList;
    public FatigueHandler fatigueHandler;
    public GroundManager groundManager;
    public InventoryManager inventoryManager;
    public PlayerManager playerManager;
    public GUIData guiData = new GUIData();

    private Timer timer = new Timer();

    private SimpleObjectProperty<Node> botInterfaceProperty;
    private SmartAgilityInfoUI infoUI;

    public SmartAgility()
    {
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask() {
        return new RootBranch();
    }

    @Override
    public void onStart(String... args)
    {
        if(CustomPlayerSense.Key.VERSION_NUM.getAsInteger() == null || CustomPlayerSense.Key.VERSION_NUM.getAsInteger() < 6)
        {
            CustomPlayerSense.clearKeys();
            getLogger().debug("Reinitialising keys!");
        }
        CustomPlayerSense.initializeKeys();

        setLoopDelay(200, 300);

        fatigueHandler = new FatigueHandler(this);
        groundManager = new GroundManager(this);
        inventoryManager = new InventoryManager(this);
        playerManager = new PlayerManager(this);
        courseList = new CourseList(this);

        getEventDispatcher().addListener(this);

        //Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR);
    }

    public void loadCourses()
    {
        courseList.loadCourses();
    }

    @Override
    public void onResume() {
        super.onResume();

        fatigueHandler.finishBreak();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty()
    {
        if (botInterfaceProperty == null)
        {
            botInterfaceProperty = new SimpleObjectProperty<>(new SmartAgilityFXUI(this));
            infoUI = new SmartAgilityInfoUI(this);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    updateInfo();
                }
            }, 0, 1000);
        }
        return botInterfaceProperty;
    }

    public void setToInfoProperty()
    {
        botInterfaceProperty.set(infoUI);
    }

    public void updateInfo()
    {
        Platform.runLater(() -> infoUI.update());
    }

    @Override
    public void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    public void onItemAdded(ItemEvent itemEvent)
    {
        if(guiData.GUIWait || isPaused()) {
            return;
        }
        ItemDefinition itemDef = itemEvent.getItem().getDefinition();
        if(itemDef == null)
        {
            return;
        }
        String itemName = itemDef.getName();
        if (itemName != null && itemName.equals("Mark of grace"))
        {
            guiData.marks++;
        }
    }

    @Override
    public void onExperienceGained(SkillEvent event) {
        if(guiData.GUIWait || isPaused())
            return;

        try
        {
            guiData.experienceGained += event.getChange();

        }
        catch(Exception e)
        {
            getLogger().warn(e);
        }
    }
}
