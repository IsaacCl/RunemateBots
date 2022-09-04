package com.SmartAgility.Leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * Created by SudoPro on 12/28/2016.
 */
public class EmptyLeaf extends LeafTask
{
    private String location = "";

    public EmptyLeaf()
    {

    }

    public EmptyLeaf(String location)
    {
        this.location = location;
    }

    @Override
    public void execute()
    {
        // Do nothing
        if (!location.equals(""))
        {
            Environment.getBot().getLogger().debug("Bad empty leaf at location: " + location);
        }
    }
}
