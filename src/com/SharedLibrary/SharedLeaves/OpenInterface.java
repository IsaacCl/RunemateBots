package com.SharedLibrary.SharedLeaves;

import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class OpenInterface extends LeafTask {

    private final ControlPanelTab interfaceWindow;

    public OpenInterface(ControlPanelTab interfaceWindow) {
        this.interfaceWindow = interfaceWindow;
    }

    @Override
    public void execute() {
        interfaceWindow.open(true);
    }
}
