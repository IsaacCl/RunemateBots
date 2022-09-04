package com.SmartAgility.Game;

import com.SmartAgility.Camera.CustomCamera;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.AbstractBot;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.abs;

public class Obstacle {

    private String name;
    private String action;
    private Area startingArea;
    private Area traversingArea;
    private Area location;
    private Area closeArea;

    private Area stuckArea;
    private Area fixArea;

    private CustomCamera camera;

    private int stuckTimes = 0;
    private int failTimes = 0;

    AbstractBot bot;


    enum ObstacleType{
        INSTANT,
        SLOW
    }
    private ObstacleType type;

    Obstacle(String name, String action, ObstacleType obstacleType, Area startingArea, Area traversingArea)
    {
        this.name = name;
        this.action = action;
        this.startingArea = startingArea;
        this.traversingArea = traversingArea;
        this.type = obstacleType;
        this.location = null;
        this.camera = new CustomCamera();
        this.bot = Environment.getBot();
    }

    Obstacle(String name, String action, ObstacleType obstacleType, Area startingArea, Area traversingArea, Coordinate location, Area closeArea, CustomCamera camera)
    {
        this.name = name;
        this.action = action;
        this.startingArea = startingArea;
        this.traversingArea = traversingArea;
        this.type = obstacleType;
        if(location != null) {
            this.location = new Area.Absolute(location);
        }
        else
        {
            this.location = null;
        }
        this.closeArea = closeArea;
        this.camera = camera;
        this.bot = Environment.getBot();
    }

    public void doObstacle(Obstacle nextObstacle)
    {
        stuckTimes = 0;

        bot.getLogger().debug("Doing obstacle: " + name);

        GameObject obstacle;

        if(location != null)
        {
            obstacle = GameObjects.newQuery().names(name).actions(action).within(location).results().nearest();
        }
        else
        {
            obstacle = GameObjects.newQuery().names(name).actions(action).results().nearest();
        }

        Player localPlayer = Players.getLocal();

        if(obstacle != null && localPlayer != null) {

            if(stuckArea != null && stuckArea.contains(localPlayer))
            {
                bot.getLogger().debug("We're stuck. Fixing");
                fixArea.interact("Walk here");
            }

            if(!obstacle.isVisible() || failTimes > 5)
            {
                camera.concurrentlyTurnTo(obstacle);
                bot.getLogger().debug("Turning to face target with custom camera!");
                Execution.delay(200, 500);
            }

            if((!obstacle.isVisible() || (failTimes > 10 && failTimes%5==1)) && closeArea != null)
            {
                RegionPath path = RegionPath.buildTo(closeArea.getRandomCoordinate());
                if(path != null) {
                    path.step();
                    bot.getLogger().debug("Found path to safe zone.");
                }
            }

            if(obstacle.interact(action))
            {
                failTimes = 0;

                Execution.delayUntil(localPlayer::isMoving,1000);

                if(!localPlayer.isMoving())
                    return;

                if(nextObstacle != null) {
                    if(Random.nextDouble(0, 1) < 0.8)
                        turnToNextObstacle(nextObstacle);
                }

                //Not sure if this is the right call because could misfire with regards to misclicks, but prevents clicking too much
                Execution.delayUntil(() ->!localPlayer.isMoving() && !startingArea.contains(localPlayer), 8000);

                bot.getLogger().debug("Bot is moving: " + localPlayer.isMoving() + " and starting area contains bot: " + startingArea.contains(localPlayer));

            }
            else
            {
                failTimes++;
                bot.getLogger().debug("Failing!");
                if(failTimes > 50)
                {
                    bot.stop("Failed more than 50 times!");
                }
            }
        }
        else
        {
            bot.getLogger().debug("We're in the area but object is not found!");
        }

    }

    public boolean readyToStart()
    {
        Player localPlayer = Players.getLocal();

        return startingArea.contains(localPlayer);
    }

    public boolean completingAction()
    {
        Player localPlayer = Players.getLocal();

        return type == ObstacleType.SLOW && traversingArea.contains(localPlayer);
    }

    public String getName()
    {
        return name;
    }

    public Area getStartingArea()
    {
        return startingArea;
    }

    public double getDistanceFromObject(Locatable object)
    {
        if(object!=null) {
            Coordinate pos = object.getPosition();
            if (pos != null) {
                return pos.distanceTo(startingArea) + (1000 * abs(pos.getPlane() - startingArea.getCenter().getPlane()));
            } else {
                return 100000;
            }
        }
        else
        {
            return 100000;
        }
    }

    public void walkToObstacle()
    {
        if(stuckArea != null)
        {
            if(stuckArea.contains(Players.getLocal())) {
                Coordinate randCoord = fixArea.getRandomCoordinate();
                bot.getLogger().debug("We're stuck. Clicking " + randCoord);
                if(!randCoord.isVisible())
                    Camera.turnTo(randCoord);
                randCoord.interact("Walk here");
            }
        }

        WebPath path;
        if(closeArea != null)
        {
            path = Traversal.getDefaultWeb().getPathBuilder().buildTo(closeArea.getRandomCoordinate());
        }
        else
        {
            path = Traversal.getDefaultWeb().getPathBuilder().buildTo(startingArea.getRandomCoordinate());
        }

        Player localPlayer = Players.getLocal();

        if(path != null && localPlayer != null)
        {
            if(path.step())
            {
                bot.getLogger().debug("Stepping towards area!");
                Execution.delayUntil(()->!localPlayer.isMoving(), 500);
                stuckTimes = 0;
            }
        }
        else if(localPlayer != null)
        {
            RegionPath path2 = RegionPath.buildTo(startingArea);
            if(path2!=null) {
                if(path2.step());
                {
                    bot.getLogger().debug("Stepping towards area 2!");
                    Execution.delayUntil(()->!localPlayer.isMoving(), 500);
                    stuckTimes = 0;
                }
            }
            else
            {

                bot.getLogger().debug("We're stuck boss!");
                if(stuckTimes > 10)
                {
                    bot.stop("Can't find exit");
                }
                stuckTimes++;
            }
        }
    }

    //This only occurs on cannifis so far, weird bug
    public void setStuckArea(Area stuckArea, Area fixArea)
    {
        this.stuckArea = stuckArea;
        this.fixArea = fixArea;
    }

    public Area getCloseArea()
    {
        return closeArea;
    }

    public Area getLocation()
    {
        return location;
    }

    private void turnToNextObstacle(Obstacle nextObstacle)
    {
        Timer timer = new Timer();
        Area nextLocation = nextObstacle.getLocation();
        Area nextCloseArea = nextObstacle.getCloseArea();
        if(nextLocation != null)
        {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Camera.concurrentlyTurnTo(nextLocation);
                }
            }, Random.nextInt(500, 3000));
        }
        if (nextCloseArea != null) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Camera.concurrentlyTurnTo(nextCloseArea.getRandomCoordinate());
                }
            }, Random.nextInt(500, 3000));
        } else {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Camera.concurrentlyTurnTo(nextObstacle.getStartingArea().getRandomCoordinate());
                }
            }, Random.nextInt(500, 3000));
        }
    }



}
