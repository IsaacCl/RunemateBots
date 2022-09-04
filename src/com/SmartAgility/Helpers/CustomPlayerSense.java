package com.SmartAgility.Helpers;

import com.runemate.game.api.hybrid.player_sense.PlayerSense;
import com.runemate.game.api.hybrid.util.calculations.Random;

import java.util.function.Supplier;

public class CustomPlayerSense {
    public static void initializeKeys() {
        for (Key key : Key.values()) {
            if (PlayerSense.get(key.name) == null) {
                PlayerSense.put(key.name, key.supplier.get());
            }
        }
    }

    public static void clearKeys()
    {
        for (Key key : Key.values()) {
            PlayerSense.clear(key.getKey());
        }
    }

    public enum Key {
        VERSION_NUM("version_num", () -> 6),   // IF YOU TOUCH ANYTHING BELOW INCREMENT THIS NUMBER AND ALSO INCREMENT IN THE .java FILE IN onStart()

        EATING_FOOD_BUFFER("eating_food_buffer", () -> Random.nextInt(0,4)),
        DRINKING_ENERGY_BUFFER("drinking_energy_buffer", () -> Random.nextInt(10,30)),
        INVENTORY_SELECTOR("inventory_selector", ()-> Random.nextInt(1, 3)),
        MAX_LAG_PATIENCE("max_lag_patience", () -> Random.nextInt(3,6)*1000),
        OVERSTAY_LEVEL("overstay_level", () -> Random.nextInt(0,4)),
        OBSTACLE_SELECTION_STRATEGY("obstacle_selection_strategy", () -> Random.nextInt(1,5)),
        LOOP_DELAY_AVERAGE("loop_delay_avg", () -> Random.nextInt(200,300)),
        LOOP_DELAY_VAR("loop_delay_var", () -> Random.nextInt(50,100)),
        BREAK_STRATEGY("break_strategy", () -> Random.nextInt(1,3))
        ;


        private final String name;
        private final Supplier supplier;

        Key(String name, Supplier supplier) {
            this.name = name;
            this.supplier = supplier;
        }

        public String getKey() {
            return name;
        }

        public Integer getAsInteger() {
            return PlayerSense.getAsInteger(name);
        }

        public Double getAsDouble() {
            return PlayerSense.getAsDouble(name);
        }

        public Long getAsLong() {
            return PlayerSense.getAsLong(name);
        }

        public Boolean getAsBoolean() {
            return PlayerSense.getAsBoolean(name);
        }
    }
}