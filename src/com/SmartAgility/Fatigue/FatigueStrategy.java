package com.SmartAgility.Fatigue;

public enum FatigueStrategy {
    TEST(1, 2, 1, 2),
    SHORT(3, 6, 20, 40),
    MEDIUM(6, 10, 40, 70),
    LONG(10, 20, 70, 100);

    public final int minBreakTime;
    public final int maxBreakTime;
    public final int minIntraBreakTime;
    public final int maxIntraBreakTime;

    FatigueStrategy(int minBreakTime, int maxBreakTime, int minIntraBreakTime, int maxIntraBreakTime) {
        this.minBreakTime = minBreakTime;
        this.maxBreakTime = maxBreakTime;
        this.minIntraBreakTime = minIntraBreakTime;
        this.maxIntraBreakTime = maxIntraBreakTime;
    }
}
