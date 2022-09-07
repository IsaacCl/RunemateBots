package com.SmartAgility.UI;

import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;

import java.util.concurrent.TimeUnit;

public class GUIData {

    public final StopWatch currentTime = new StopWatch();
    public boolean GUIWait = true;
    public int marks = 0;

    public int experienceGained = 0;
    public int MaxRuntime;
    public boolean UseBoredomSystem;
    public boolean UseSeersTeleport;
    public String PotionType;
    public String FoodType;
    public int PotionAmount;
    public int FoodAmount;
    public String currentTask = "";
    public String currentArea = "";
    public boolean doGnome = false;
    public boolean doDraynor = false;
    public boolean doAlKharid = false;
    public boolean doVarrock = false;
    public boolean doCanifis = false;
    public boolean doSeers = false;
    public boolean doPollnivneach = false;
    public boolean eatFood = false;
    public boolean drinkPotions = false;
    private int FoodHealth;
    private int NextFoodHealth;

    public double getExperiencePerHour() {
        if (experienceGained == 0)
            return 0;
        return (experienceGained + 0.0) / ((double) currentTime.getRuntime(TimeUnit.MILLISECONDS) / 3600000.0);
    }

    public double getMarksPerHour() {
        if (marks == 0)
            return 0;

        return (marks + 0.0) / ((double) currentTime.getRuntime(TimeUnit.MILLISECONDS) / 3600000.0);
    }

    public void randomiseNextFoodHealth() {
        NextFoodHealth = FoodHealth + Random.nextInt(-2, 2);
    }

    public int getFoodHealth() {
        return NextFoodHealth;
    }

    public void setFoodHealth(int health) {
        FoodHealth = health;
    }

}
