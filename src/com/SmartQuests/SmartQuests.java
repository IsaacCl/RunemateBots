package com.SmartQuests;

import com.SmartQuests.branches.IsEngagedInConversation;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;


/*
Design principles:
- Maximum of one action per cycle.
- Some execution delay per cycle for performance reasons unless click spamming.
- Prefer events for tracking game state over recording ingame events
- As much logic in tree as possible
 */


public class SmartQuests extends TreeBot {
    @Override
    public TreeTask createRootTask() {
        return new IsEngagedInConversation();
    }
}