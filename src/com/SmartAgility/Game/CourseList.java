package com.SmartAgility.Game;

import com.SmartAgility.Camera.CustomCamera;
import com.SmartAgility.Helpers.CustomPlayerSense;
import com.SmartAgility.SmartAgility;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.details.Locatable;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CourseList  {

    private ArrayList<Course> courses = new ArrayList<>();

    private Course currentCourse;

    private SmartAgility bot;

    private Course GnomeStronghold;
    private Course DraynorRooftop;
    private Course AlKharidRooftop;
    private Course VarrockRooftop;
    private Course CanifisRooftop;
    private Course SeersRooftop;
    private Course PollnivneachRooftop;

    public CourseList(SmartAgility bot)
    {
        this.bot = bot;

        GnomeStronghold = getGnomeStronghold();
        DraynorRooftop = getDraynorRooftop();
        AlKharidRooftop = getAlKharidRooftop();
        VarrockRooftop = getVarrockRooftop();
        CanifisRooftop = getCannifisRooftop();
        SeersRooftop = getSeersRooftop();
        PollnivneachRooftop = getPollnivneachRooftop();
    }

    private boolean coursesLoaded = false;
    public void loadCourses()
    {
        if(bot.guiData.doGnome) {
            bot.getLogger().debug("Adding gnome stronghold");
            courses.add(GnomeStronghold);
        }
        if(bot.guiData.doDraynor) {
            bot.getLogger().debug("Adding draynor");
            courses.add(DraynorRooftop);
        }
        if(bot.guiData.doAlKharid)
        {
            bot.getLogger().debug("Adding al kharid");
            courses.add(AlKharidRooftop);
        }
        if(bot.guiData.doVarrock) {
            bot.getLogger().debug("Adding varrock");
            courses.add(VarrockRooftop);
        }
        if(bot.guiData.doCanifis) {
            bot.getLogger().debug("Adding canifis");
            courses.add(CanifisRooftop);
        }
        if(bot.guiData.doSeers) {
            bot.getLogger().debug("Adding seers");
            courses.add(SeersRooftop);
        }

        if(bot.guiData.doPollnivneach)
        {
            bot.getLogger().debug("Adding pollnivneach");
            courses.add(PollnivneachRooftop);
        }
        coursesLoaded = true;
    }

    private void loadCurrentCourse()
    {
        while(true) {
            getNearestCourse();
            if(currentCourse!=null)
            {
                return;
            }
            Execution.delay(1000, 2000);
            bot.getLogger().debug("Trying again.");
        }
    }

    public void doNextObstacle() {
        currentCourse.doNextObstacle();
    }

    public boolean doingObstacle(){

        return currentCourse.doingObstacle();
    }

    private void getNearestCourse()
    {
        Execution.delayUntil(() -> coursesLoaded, 1000);
        Player player = Players.getLocal();
        if(player == null)
        {
            return;
        }
        Course bestCourse = null;
        double bestDistance = 100000;
        for(Course course : courses)
        {
            double distance = course.getDistanceFromCourse(player);
            bot.getLogger().debug("Distance = " + distance);
            if(distance < bestDistance)
            {
                bestCourse = course;
                bestDistance = distance;
            }
        }

        if(bestDistance < 50)
        {
            bot.getLogger().debug("We're close to a course already.");
            currentCourse = bestCourse;
            bot.guiData.currentArea = bestCourse.name;
            bot.getLogger().debug("Course name: " + bestCourse.name);
        }
        else
        {
            getClosestLevelCourse();
            bot.getLogger().debug("Getting next course based on level.");
        }
    }

    private void getClosestLevelCourse()
    {
        Execution.delayUntil(() -> coursesLoaded, 1000);

        bot.getLogger().debug("Checking to see if we're at the right course for our level.");

        if(Skill.AGILITY.getCurrentLevel() > 80+ CustomPlayerSense.Key.OVERSTAY_LEVEL.getAsInteger() || courses.size() == 0)
        {
            bot.getLogger().debug("Stopping bot!!!!!!!!!!!!!!!!!!");
            Environment.getBot().stop("No relevant courses were selected");
        }

        Course bestCourse = currentCourse;
        int maxLevel;
        if(bestCourse != null) {
            maxLevel = bestCourse.minLevel;
        }
        else
        {
            maxLevel = -1;
        }
        int level = Skill.AGILITY.getCurrentLevel();
        for(Course course :courses)
        {
            if(level >= course.minLevel && course.minLevel > maxLevel)
            {
                maxLevel = course.minLevel ;
                bestCourse = course;
            }
        }


        currentCourse = bestCourse;

        if(bestCourse != null)
            bot.guiData.currentArea = bestCourse.name;
    }

    public void checkCurrentCourse(){

        //lazy loading since otherwise can't find the nearest course
        if(currentCourse == null)
        {
            bot.getLogger().debug("Checking to see which course we're at right now.");
            loadCurrentCourse();
            return;
        }

        getClosestLevelCourse();
    }

    public boolean isPlayerInSameArea(Locatable object)
    {
        return currentCourse.isPlayerInSameArea(object);
    }

    public boolean isPlayerInFirstArea()
    {

        return currentCourse.isPlayerInFirstArea();
    }

    private Course getGnomeStronghold()
    {
        Obstacle logBalance = new Obstacle("Log balance", "Walk-across", Obstacle.ObstacleType.SLOW,
        new Area.Polygonal(new Coordinate(2470, 3436, 0), new Coordinate(2472, 3439, 0), new Coordinate(2487, 3439, 0), new Coordinate(2488, 3437, 0), new Coordinate(2482, 3437, 0),new Coordinate(2482, 3436, 0)), new Area.Rectangular(new Coordinate(2474, 3435, 0), new Coordinate(2474, 3430, 0)),
                null, new Area.Rectangular(new Coordinate(2477, 3436, 0), new Coordinate(2471, 3437, 0)), new CustomCamera());
        Obstacle obstacleNet = new Obstacle("Obstacle net", "Climb-over", Obstacle.ObstacleType.INSTANT,
        new Area.Rectangular(new Coordinate(2470, 3429, 0), new Coordinate(2477, 3426, 0)), null);
        Obstacle treeBranch = new Obstacle("Tree branch", "Climb", Obstacle.ObstacleType.INSTANT,
        new Area.Rectangular(new Coordinate(2471, 3424, 1), new Coordinate(2476, 3422, 1)), null);
        Obstacle balancingRope = new Obstacle("Balancing rope", "Walk-on", Obstacle.ObstacleType.SLOW,
        new Area.Rectangular(new Coordinate(2472, 3420, 2), new Coordinate(2477, 3419, 2)), new Area.Rectangular(new Coordinate(2478, 3420, 2), new Coordinate(2482, 3420, 2)),
                null, new Area.Rectangular(new Coordinate(2477, 3419,2), new Coordinate(2476, 3420, 2)), new CustomCamera());
        Obstacle treeBranch2 = new Obstacle("Tree branch", "Climb-down", Obstacle.ObstacleType.INSTANT,
        new Area.Rectangular(new Coordinate(2483, 3420, 2), new Coordinate(2485, 3419, 2)), null);
        Obstacle obstacleNet2 = new Obstacle("Obstacle net", "Climb-over", Obstacle.ObstacleType.INSTANT,
        new Area.Rectangular(new Coordinate(2489, 3420, 0), new Coordinate(2481, 3425, 0)), null);
        Obstacle obstaclePipe = new Obstacle("Obstacle pipe", "Squeeze-through", Obstacle.ObstacleType.SLOW,
        new Area.Rectangular(new Coordinate(2482, 3427, 0), new Coordinate(2490, 3430, 0)), new Area.Rectangular(new Coordinate(2484, 3431, 0), new Coordinate(2488, 3436, 0)));


        Area[] bankArea = {
                new Area.Rectangular(new Coordinate(2445, 3427, 1), new Coordinate(2445, 3422, 1)),
                new Area.Rectangular(new Coordinate(2446, 3422, 1), new Coordinate(2446, 3423, 1)),
                new Area.Rectangular(new Coordinate(2446, 3427, 1), new Coordinate(2446, 3427, 1)),
                new Area.Rectangular(new Coordinate(2446, 3426, 1), new Coordinate(2446, 3426, 1)),
                new Area.Rectangular(new Coordinate(2446, 3424, 1), new Coordinate(2446, 3424, 1))
        };
        CustomBank bank = new CustomBank(bot, getAbsoluteArea(bankArea));
        //Haven't added bank yet.

        return new Course("Gnome Stronghold", 1, logBalance, obstacleNet, treeBranch, balancingRope, treeBranch2, obstacleNet2, obstaclePipe);
    }

    private Course getDraynorRooftop()
    {

        Obstacle roughWall = new Obstacle("Rough wall", "Climb", Obstacle.ObstacleType.INSTANT,
                new Area.Rectangular(new Coordinate(3103, 3281, 0), new Coordinate(3106, 3268, 0)), null, null, new Area.Rectangular(new Coordinate(3103, 3281, 0), new Coordinate(3105, 3275, 0)), new CustomCamera(-1, 0));
        Obstacle tightRope = new Obstacle("Tightrope", "Cross", Obstacle.ObstacleType.SLOW,
                        new Area.Rectangular(new Coordinate(3099, 3277, 3), new Coordinate(3102, 3281, 3)),
                        new Area.Rectangular(new Coordinate(3098, 3277, 3), new Coordinate(3090, 3277, 3)));
        Obstacle tightRope2 = new Obstacle("Tightrope", "Cross", Obstacle.ObstacleType.SLOW,
                        new Area.Polygonal(new Coordinate(3090, 3277, 3), new Coordinate(3088,3275,3), new Coordinate(3090,3273,3), new Coordinate(3091, 3276, 3), new Coordinate(3092, 3276, 3)),
                        new Area.Rectangular(new Coordinate(3092, 3275, 3), new Coordinate(3092, 3267, 3)));

        Area narrowWallArea = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3093, 3266, 3), new Coordinate(3091, 3265, 3)),
                new Area.Rectangular(new Coordinate(3090, 3265, 3), new Coordinate(3089, 3265, 3))
        });

        Obstacle narrowWall = new Obstacle("Narrow wall", "Balance", Obstacle.ObstacleType.SLOW,
                        narrowWallArea,
                        new Area.Rectangular(new Coordinate(3089, 3264, 3), new Coordinate(3089, 3262, 3)));
        Obstacle wall = new Obstacle("Wall", "Jump-up", Obstacle.ObstacleType.INSTANT,
                        new Area.Rectangular(new Coordinate(3088, 3261, 3), new Coordinate(3088, 3257, 3)), null);
        Obstacle gap = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                        new Area.Rectangular(new Coordinate(3087, 3255, 3), new Coordinate(3094, 3255, 3)), null);
        Obstacle crate = new Obstacle("Crate", "Climb-down", Obstacle.ObstacleType.SLOW,
                new Area.Rectangular(new Coordinate(3101, 3261, 3), new Coordinate(3096, 3256, 3)),
                new Area.Absolute(new Coordinate(3102, 3261, 1)), null, new Area.Rectangular(new Coordinate(3099, 3261, 3), new Coordinate(3101, 3259, 3)), new CustomCamera());

        CustomBank bank = new CustomBank(bot, new Area.Rectangular(new Coordinate(3092, 3245, 0), new Coordinate(3093, 3241, 0)));

        return new Course("Draynor Rooftop", 10,bank, roughWall, tightRope, tightRope2, narrowWall, wall, gap, crate);

    }

    private Course getAlKharidRooftop()
    {
        Area[] roughWallArea = {
                new Area.Rectangular(new Coordinate(3270, 3196, 0), new Coordinate(3277, 3195, 0)),
                new Area.Rectangular(new Coordinate(3270, 3197, 0), new Coordinate(3284, 3199, 0)),
                new Area.Rectangular(new Coordinate(3270, 3200, 0), new Coordinate(3288, 3203, 0))
        };

        Obstacle roughWall = new Obstacle("Rough wall", "Climb", Obstacle.ObstacleType.INSTANT,
                getAbsoluteArea(roughWallArea), null, null,
                new Area.Rectangular(new Coordinate(3277, 3195, 0), new Coordinate(3270, 3198, 0)),
                new CustomCamera(0, -1));

        Area[] tightRopeArea = {
                new Area.Rectangular(new Coordinate(3271, 3192, 3), new Coordinate(3276, 3190, 3)),
                new Area.Rectangular(new Coordinate(3273, 3189, 3), new Coordinate(3276, 3185, 3)),
                new Area.Rectangular(new Coordinate(3277, 3187, 3), new Coordinate(3278, 3185, 3)),
                new Area.Rectangular(new Coordinate(3273, 3184, 3), new Coordinate(3274, 3180, 3)),
                new Area.Rectangular(new Coordinate(3272, 3182, 3), new Coordinate(3272, 3180, 3))
        };

        Area[] tightRopeSafeArea = {
                new Area.Rectangular(new Coordinate(3272, 3182, 3), new Coordinate(3274, 3181, 3)),
                new Area.Rectangular(new Coordinate(3273, 3180, 3), new Coordinate(3274, 3180, 3))
        };

        Obstacle tightRope = new Obstacle("Tightrope", "Cross", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(tightRopeArea), new Area.Rectangular(new Coordinate(3272, 3179, 3), new Coordinate(3272, 3173, 3)),
                null, getAbsoluteArea(tightRopeSafeArea), new CustomCamera());

        Area[] cableArea = {
                new Area.Rectangular(new Coordinate(3265, 3173, 3), new Coordinate(3271, 3168, 3)),
                new Area.Rectangular(new Coordinate(3272, 3172, 3), new Coordinate(3272, 3168, 3)),
                new Area.Rectangular(new Coordinate(3265, 3167, 3), new Coordinate(3265, 3161, 3)),
                new Area.Rectangular(new Coordinate(3266, 3166, 3), new Coordinate(3268, 3165, 3)),
                new Area.Rectangular(new Coordinate(3266, 3163, 3), new Coordinate(3272, 3161, 3))
        };

        Area[] cableSafeArea = {
                new Area.Rectangular(new Coordinate(3266, 3166, 3), new Coordinate(3268, 3165, 3)),
                new Area.Rectangular(new Coordinate(3265, 3167, 3), new Coordinate(3265, 3164, 3))
        };

        Obstacle cable = new Obstacle("Cable", "Swing-across", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(cableArea), new Area.Rectangular(new Coordinate(3269, 3166, 3), new Coordinate(3282, 3166, 3)),
                null, getAbsoluteArea(cableSafeArea), new CustomCamera());

        Area[] ziplineArea = {
                new Area.Rectangular(new Coordinate(3283, 3176, 3), new Coordinate(3287, 3160, 3)),
                new Area.Rectangular(new Coordinate(3288, 3167, 3), new Coordinate(3294, 3160, 3)),
                new Area.Rectangular(new Coordinate(3295, 3169, 3), new Coordinate(3302, 3164, 3)),
                new Area.Rectangular(new Coordinate(3302, 3162, 3), new Coordinate(3295, 3160, 3)),
                new Area.Rectangular(new Coordinate(3295, 3163, 3), new Coordinate(3301, 3163, 3))
        };

        Area[] ziplineSafeArea = {
                new Area.Rectangular(new Coordinate(3295, 3165, 3), new Coordinate(3302, 3164, 3)),
                new Area.Rectangular(new Coordinate(3295, 3163, 3), new Coordinate(3301, 3163, 3)),
                new Area.Rectangular(new Coordinate(3295, 3162, 3), new Coordinate(3302, 3161, 3))
        };

        Obstacle zipline = new Obstacle("Zip line", "Teeth-grip", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(ziplineArea), new Area.Rectangular(new Coordinate(3302, 3163, 1), new Coordinate(3314, 3163, 1)),
                null, getAbsoluteArea(ziplineSafeArea), new CustomCamera());

        Area[] tropicalTreeArea = {
                new Area.Rectangular(new Coordinate(3313, 3160, 1), new Coordinate(3318, 3161, 1)),
                new Area.Rectangular(new Coordinate(3313, 3165, 1), new Coordinate(3318, 3165, 1)),
                new Area.Rectangular(new Coordinate(3316, 3162, 1), new Coordinate(3318, 3164, 1)),
                new Area.Rectangular(new Coordinate(3315, 3163, 1), new Coordinate(3315, 3163, 1))
        };

        Obstacle tropicalTree = new Obstacle("Tropical tree", "Swing-across", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(tropicalTreeArea), new Area.Rectangular(new Coordinate(3318, 3166, 1), new Coordinate(3316, 3172, 1)));

        Area[] roofTopBeamsArea = {
                new Area.Rectangular(new Coordinate(3318, 3173, 2), new Coordinate(3314, 3178, 2)),
                new Area.Rectangular(new Coordinate(3312, 3178, 2), new Coordinate(3313, 3178, 2))
        };

        Obstacle roofTopBeams = new Obstacle("Roof top beams", "Climb", Obstacle.ObstacleType.INSTANT,
                getAbsoluteArea(roofTopBeamsArea), null);

        Area[] tightRope2Area = {
                new Area.Rectangular(new Coordinate(3312, 3186, 3), new Coordinate(3312, 3180, 3)),
                new Area.Rectangular(new Coordinate(3313, 3185, 3), new Coordinate(3313, 3180, 3)),
                new Area.Rectangular(new Coordinate(3314, 3186, 3), new Coordinate(3318, 3180, 3))
        };

        Area[] tightRope2TravelArea = {
                new Area.Rectangular(new Coordinate(3311, 3186, 3), new Coordinate(3305, 3186, 3)),
                new Area.Rectangular(new Coordinate(3302, 3186, 3), new Coordinate(3302, 3186, 3))
        };

        Obstacle tightRope2 = new Obstacle("Tightrope", "Cross", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(tightRope2Area), getAbsoluteArea(tightRope2TravelArea));


        Area[] gapArea = {
                new Area.Rectangular(new Coordinate(3301, 3187, 3), new Coordinate(3304, 3187, 3)),
                new Area.Rectangular(new Coordinate(3300, 3188, 3), new Coordinate(3305, 3188, 3)),
                new Area.Rectangular(new Coordinate(3299, 3189, 3), new Coordinate(3305, 3189, 3)),
                new Area.Rectangular(new Coordinate(3298, 3190, 3), new Coordinate(3304, 3190, 3)),
                new Area.Rectangular(new Coordinate(3298, 3191, 3), new Coordinate(3303, 3191, 3)),
                new Area.Rectangular(new Coordinate(3299, 3192, 3), new Coordinate(3302, 3192, 3)),
                new Area.Rectangular(new Coordinate(3300, 3193, 3), new Coordinate(3301, 3193, 3))
        };

        Obstacle gap = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                getAbsoluteArea(gapArea), null);

        CustomBank bank = new CustomBank(bot, new Area.Rectangular(new Coordinate(3269, 3170, 0), new Coordinate(3270, 3164, 0)));

        return new Course("Al Kharid Rooftop", 20, bank, roughWall, tightRope, cable, zipline, tropicalTree, roofTopBeams, tightRope2, gap);
    }

    private Course getVarrockRooftop()
    {

        Area[] roughWallArea = {
                new Area.Rectangular(new Coordinate(3221, 3419, 0), new Coordinate(3240, 3417, 0)),
                new Area.Rectangular(new Coordinate(3221, 3416, 0), new Coordinate(3234, 3410, 0))
        };

        Area[] roughWallSafeArea = {
                new Area.Rectangular(new Coordinate(3221, 3418, 0), new Coordinate(3222, 3410, 0)),
                new Area.Rectangular(new Coordinate(3223, 3412, 0), new Coordinate(3223, 3416, 0)),
                new Area.Rectangular(new Coordinate(3224, 3417, 0), new Coordinate(3223, 3417, 0)),
                new Area.Rectangular(new Coordinate(3224, 3416, 0), new Coordinate(3224, 3414, 0))
        };

        Obstacle roughWall = new Obstacle("Rough wall", "Climb", Obstacle.ObstacleType.INSTANT,
                getAbsoluteArea(roughWallArea), null, null, getAbsoluteArea(roughWallSafeArea), new CustomCamera(-1, 0));

        Area.Absolute cLArea = getAbsoluteArea( new Area[]{
                new Area.Rectangular(new Coordinate(3214, 3419, 3), new Coordinate(3214, 3410, 3)),
                new Area.Rectangular(new Coordinate(3215, 3410, 3), new Coordinate(3217, 3410, 3)),
                new Area.Rectangular(new Coordinate(3217, 3411, 3), new Coordinate(3219, 3411, 3)),
                new Area.Rectangular(new Coordinate(3219, 3419, 3), new Coordinate(3219, 3412, 3)),
                new Area.Rectangular(new Coordinate(3215, 3419, 3), new Coordinate(3218, 3419, 3))
        });
        Obstacle clothesLine = new Obstacle("Clothes line", "Cross", Obstacle.ObstacleType.SLOW, cLArea,
                new Area.Rectangular(new Coordinate(3213, 3414, 3), new Coordinate(3209, 3414, 3)),
                null, new Area.Rectangular(new Coordinate(3214, 3418, 3), new Coordinate(3214, 3411, 3)), new CustomCamera());

        Area.Absolute gapArea = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3208, 3414, 3), new Coordinate(3203, 3414, 3)),
                new Area.Rectangular(new Coordinate(3203, 3415, 3), new Coordinate(3202, 3415, 3)),
                new Area.Rectangular(new Coordinate(3202, 3416, 3), new Coordinate(3201, 3416, 3)),
                new Area.Rectangular(new Coordinate(3202, 3417, 3), new Coordinate(3201, 3417, 3))
        });

        Area gapCloseArea = getAbsoluteArea (new Area[]{
                new Area.Rectangular(new Coordinate(3214, 3418, 3), new Coordinate(3214, 3411, 3)),
                new Area.Rectangular(new Coordinate(3202, 3417, 3), new Coordinate(3201, 3417, 3)),
                new Area.Rectangular(new Coordinate(3202, 3416, 3), new Coordinate(3201, 3416, 3)),
                new Area.Rectangular(new Coordinate(3203, 3415, 3), new Coordinate(3202, 3415, 3))
        });

        Obstacle gap = new Obstacle("Gap", "Leap", Obstacle.ObstacleType.INSTANT, gapArea, null, null, gapCloseArea, new CustomCamera());

        Obstacle balanceWall = new Obstacle("Wall", "Balance", Obstacle.ObstacleType.SLOW,
                new Area.Rectangular(new Coordinate(3197, 3416, 1), new Coordinate(3193, 3416, 1)),
                        new Area.Rectangular(new Coordinate(3190, 3414, 1), new Coordinate(3190, 3407, 1)));

        Obstacle gap2 = new Obstacle("Gap", "Leap", Obstacle.ObstacleType.INSTANT,
                new Area.Rectangular(new Coordinate(3192, 3406, 3), new Coordinate(3198, 3402, 3)), null,
                null, new Area.Rectangular(new Coordinate(3197, 3402, 3), new Coordinate(3194, 3403, 3)), new CustomCamera(true));

        Area.Absolute gapArea2 = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3191, 3398, 3), new Coordinate(3202, 3395, 3)),
                new Area.Rectangular(new Coordinate(3202, 3399, 3), new Coordinate(3202, 3403, 3)),
                new Area.Rectangular(new Coordinate(3203, 3403, 3), new Coordinate(3208, 3403, 3)),
                new Area.Rectangular(new Coordinate(3208, 3402, 3), new Coordinate(3208, 3395, 3)),
                new Area.Rectangular(new Coordinate(3207, 3395, 3), new Coordinate(3203, 3395, 3))
        });

        Coordinate gap3Coord = new Coordinate(3209, 3397, 3);

        Area[] gap3SafeArea = {
                new Area.Rectangular(new Coordinate(3205, 3403, 3), new Coordinate(3208, 3403, 3)),
                new Area.Rectangular(new Coordinate(3208, 3400, 3), new Coordinate(3208, 3402, 3))
        };

        Obstacle gap3 = new Obstacle("Gap", "Leap", Obstacle.ObstacleType.INSTANT, gapArea2, null, gap3Coord,
                getAbsoluteArea(gap3SafeArea), new CustomCamera());

        Area gapArea4 = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3218, 3402, 3), new Coordinate(3218, 3393, 3)),
                new Area.Rectangular(new Coordinate(3219, 3393, 3), new Coordinate(3221, 3393, 3)),
                new Area.Rectangular(new Coordinate(3221, 3394, 3), new Coordinate(3225, 3394, 3)),
                new Area.Rectangular(new Coordinate(3225, 3393, 3), new Coordinate(3232, 3393, 3)),
                new Area.Rectangular(new Coordinate(3232, 3394, 3), new Coordinate(3232, 3402, 3)),
                new Area.Rectangular(new Coordinate(3231, 3402, 3), new Coordinate(3219, 3402, 3))
        });

        Coordinate gap4Coord = new Coordinate(3233, 3402, 3);

        Obstacle gap4 = new Obstacle("Gap", "Leap", Obstacle.ObstacleType.INSTANT, gapArea4, null, gap4Coord,
                new Area.Rectangular(new Coordinate(3227, 3402, 3), new Coordinate(3232, 3402, 3)), new CustomCamera());

        Obstacle ledge = new Obstacle("Ledge", "Hurdle", Obstacle.ObstacleType.INSTANT,
                new Area.Rectangular(new Coordinate(3236, 3403, 3), new Coordinate(3240, 3408, 3)), null);

        Area edgeArea = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3236, 3415, 3), new Coordinate(3240, 3415, 3)),
                new Area.Rectangular(new Coordinate(3240, 3414, 3), new Coordinate(3240, 3410, 3)),
                new Area.Rectangular(new Coordinate(3239, 3410, 3), new Coordinate(3236, 3410, 3)),
                new Area.Rectangular(new Coordinate(3236, 3414, 3), new Coordinate(3236, 3411, 3))
        });

        Obstacle edge = new Obstacle("Edge", "Jump-off", Obstacle.ObstacleType.SLOW, edgeArea,
                new Area.Rectangular(new Coordinate(3236, 3416, 3), new Coordinate(3240, 3416, 3)),
                null, new Area.Rectangular(new Coordinate(3236, 3415, 3), new Coordinate(3240, 3415, 3)), new CustomCamera());

        CustomBank bank = new CustomBank(bot, new Area.Rectangular(new Coordinate(3185, 3443, 0), new Coordinate(3183, 3436, 0)));

        return new Course("Varrock Rooftop", 30, bank, roughWall, clothesLine, gap, balanceWall, gap2, gap3, gap4, ledge, edge);
    }


    private Course getCannifisRooftop()
    {
        Area tallTreeArea = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3511, 3485, 0), new Coordinate(3505, 3485, 0)),
                new Area.Rectangular(new Coordinate(3508, 3486, 0), new Coordinate(3505, 3488, 0))
        });

        Obstacle tallTree = new Obstacle("Tall tree", "Climb", Obstacle.ObstacleType.SLOW,
                tallTreeArea, new Area.Rectangular(new Coordinate(3507, 3489, 0), new Coordinate(3505, 3489, 0)), null,new Area.Rectangular(new Coordinate(3505, 3489, 0), new Coordinate(3508, 3487, 0)), new CustomCamera(0, 1));

        Area gapArea = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3507, 3492, 2), new Coordinate(3506, 3497, 2)),
                new Area.Rectangular(new Coordinate(3505, 3495, 2), new Coordinate(3505, 3497, 2)),
                new Area.Rectangular(new Coordinate(3508, 3494, 2), new Coordinate(3508, 3497, 2)),
                new Area.Rectangular(new Coordinate(3509, 3495, 2), new Coordinate(3509, 3496, 2)),
                new Area.Rectangular(new Coordinate(3510, 3495, 2), new Coordinate(3510, 3495, 2))
        });


        Obstacle gap = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                gapArea, null, new Coordinate(3505, 3498, 2), null, new CustomCamera());

        gap.setStuckArea(new Area.Absolute(new Coordinate(3505, 3489, 2)),
                new Area.Rectangular(new Coordinate(3507, 3492, 2), new Coordinate(3506, 3493, 2)));


        Obstacle gap2 = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                new Area.Rectangular(new Coordinate(3503, 3506, 2), new Coordinate(3496, 3504, 2)), null,
                new Coordinate(3496, 3504, 2), null, new CustomCamera());

        gap2.setStuckArea(new Area.Absolute(new Coordinate(3505, 3498, 2)),
                new Area.Rectangular(new Coordinate(3503, 3504, 2), new Coordinate(3502, 3505, 2)));

        Area gap3Area = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3487, 3501, 2), new Coordinate(3491, 3499, 2)),
                new Area.Rectangular(new Coordinate(3492, 3500, 2), new Coordinate(3492, 3504, 2)),
                new Area.Rectangular(new Coordinate(3491, 3504, 2), new Coordinate(3490, 3502, 2)),
                new Area.Rectangular(new Coordinate(3489, 3502, 2), new Coordinate(3489, 3502, 2))
        });

        Obstacle gap3 = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                gap3Area, null, new Coordinate(3485, 3499, 2), null, new CustomCamera());

        Area gap4Area = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3479, 3499, 3), new Coordinate(3478, 3492, 3)),
                new Area.Rectangular(new Coordinate(3477, 3498, 3), new Coordinate(3475, 3492, 3))
        });

        Obstacle gap4 = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                gap4Area, null, new Coordinate(3478, 3491, 3), null, new CustomCamera());

        Area vaultArea = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3484, 3487, 2), new Coordinate(3477, 3485, 2)),
                new Area.Rectangular(new Coordinate(3482, 3484, 2), new Coordinate(3477, 3484, 2)),
                new Area.Rectangular(new Coordinate(3481, 3483, 2), new Coordinate(3481, 3481, 2)),
                new Area.Rectangular(new Coordinate(3477, 3483, 2), new Coordinate(3479, 3481, 2)),
                new Area.Rectangular(new Coordinate(3480, 3481, 2), new Coordinate(3480, 3482, 2))
        });

        Obstacle vault = new Obstacle("Pole-vault", "Vault", Obstacle.ObstacleType.INSTANT,
                vaultArea, null);


        Area gap5Area = getAbsoluteArea( new Area[]{
                new Area.Rectangular(new Coordinate(3489, 3476, 3), new Coordinate(3499, 3469, 3)),
                new Area.Rectangular(new Coordinate(3500, 3476, 3), new Coordinate(3502, 3471, 3)),
                new Area.Rectangular(new Coordinate(3503, 3476, 3), new Coordinate(3503, 3472, 3)),
                new Area.Rectangular(new Coordinate(3490, 3477, 3), new Coordinate(3497, 3477, 3)),
                new Area.Rectangular(new Coordinate(3491, 3478, 3), new Coordinate(3496, 3478, 3))
        });

        Obstacle gap5 = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                gap5Area,  null, null, new Area.Rectangular(new Coordinate(3500, 3476, 3), new Coordinate(3503, 3472, 3)),
                new CustomCamera());

        gap5.setStuckArea(new Area.Absolute(new Coordinate(3486, 3476, 3), new Coordinate(3487, 3476, 3)),
                new Area.Rectangular(new Coordinate(3489, 3476, 3), new Coordinate(3490, 3473, 3)));

        Area gap6Area = getAbsoluteArea(new Area[] {
                new Area.Rectangular(new Coordinate(3510, 3475, 2), new Coordinate(3511, 3482, 2)),
                new Area.Rectangular(new Coordinate(3509, 3479, 2), new Coordinate(3509, 3481, 2)),
                new Area.Rectangular(new Coordinate(3512, 3482, 2), new Coordinate(3515, 3477, 2)),
                new Area.Rectangular(new Coordinate(3512, 3476, 2), new Coordinate(3512, 3476, 2))
        });

        Obstacle gap6 = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT,
                gap6Area, null, new Coordinate(3510, 3483, 2), null, new CustomCamera());

        CustomBank bank = new CustomBank(bot, new Area.Rectangular(new Coordinate(3511, 3482, 0), new Coordinate(3512, 3478, 0)));

        return new Course("Canifis Rooftop",40, bank, tallTree, gap, gap2, gap3, gap4, vault, gap5, gap6);

    }

    private Course getSeersRooftop()
    {

        Area[] wallArea = {
                new Area.Rectangular(new Coordinate(2728, 3489, 0), new Coordinate(2730, 3487, 0)),
                new Area.Rectangular(new Coordinate(2728, 3486, 0), new Coordinate(2732, 3484, 0)),
                new Area.Rectangular(new Coordinate(2724, 3486, 0), new Coordinate(2727, 3476, 0)),
                new Area.Rectangular(new Coordinate(2723, 3481, 0), new Coordinate(2723, 3476, 0))
        };

        Area safeWallArea = new Area.Rectangular(new Coordinate(2728, 3489, 0), new Coordinate(2730, 3487, 0));

        Obstacle wall = new Obstacle("Wall", "Climb-up", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(wallArea), new Area.Absolute(new Coordinate(2729, 3488, 1)),
                null, safeWallArea, new CustomCamera(0, 1));

        Obstacle gap = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.SLOW,
                new Area.Rectangular(new Coordinate(2730, 3490, 3), new Coordinate(2721, 3497, 3)),
                new Area.Absolute(new Coordinate(2710, 3488, 2)), null,
                new Area.Rectangular(new Coordinate(2722, 3492, 3), new Coordinate(2723, 3495, 3)), new CustomCamera());

        Area[] tightRopeArea = {
                new Area.Rectangular(new Coordinate(2713, 3494, 2), new Coordinate(2705, 3493, 2)),
                new Area.Rectangular(new Coordinate(2710, 3495, 2), new Coordinate(2705, 3495, 2)),
                new Area.Rectangular(new Coordinate(2710, 3492, 2), new Coordinate(2707, 3488, 2))
        };

        Obstacle tightRope = new Obstacle("Tightrope", "Cross", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(tightRopeArea), new Area.Rectangular(new Coordinate(2710, 3487, 2), new Coordinate(2710, 3481, 2)));

        Area[] gap2Area = {
                new Area.Rectangular(new Coordinate(2715, 3477, 2), new Coordinate(2710, 3480, 2)),
                new Area.Rectangular(new Coordinate(2711, 3481, 2), new Coordinate(2715, 3481, 2))
        };

        Obstacle gap2 = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT, getAbsoluteArea(gap2Area), null );

        Area[] gap3Area = {
                new Area.Rectangular(new Coordinate(2716, 3471, 3), new Coordinate(2713, 3471, 3)),
                new Area.Rectangular(new Coordinate(2716, 3472, 3), new Coordinate(2705, 3472, 3)),
                new Area.Rectangular(new Coordinate(2704, 3476, 3), new Coordinate(2700, 3470, 3))
        };

        Obstacle gap3 = new Obstacle("Gap", "Jump", Obstacle.ObstacleType.INSTANT, getAbsoluteArea(gap3Area) , null,
                new Coordinate(2700, 3469, 3), null, new CustomCamera());

        Obstacle edge = new Obstacle("Edge", "Jump", Obstacle.ObstacleType.INSTANT,
                new Area.Rectangular(new Coordinate(2698, 3465, 2), new Coordinate(2702, 3460, 2)), null);

        CustomBank bank = new CustomBank(bot, new Area.Rectangular(new Coordinate(2729, 3493, 0),
                new Coordinate(2722, 3492, 0)));

        return new Course("Seers Rooftop", 60, bank, wall, gap, tightRope, gap2, gap3, edge);
    }

    private Course getPollnivneachRooftop()
    {
        Area[] basketArea = {
                new Area.Rectangular(new Coordinate(3345, 2962, 0), new Coordinate(3352, 2957, 0)),
                new Area.Rectangular(new Coordinate(3353, 2961, 0), new Coordinate(3354, 2962, 0)),
                new Area.Rectangular(new Coordinate(3352, 2968, 0), new Coordinate(3359, 2963, 0))
        };

        Area[] basketSafeArea = {
                new Area.Rectangular(new Coordinate(3352, 2962, 0), new Coordinate(3353, 2961, 0)),
                new Area.Rectangular(new Coordinate(3351, 2961, 0), new Coordinate(3349, 2961, 0)),
                new Area.Rectangular(new Coordinate(3350, 2962, 0), new Coordinate(3349, 2962, 0))
        };

        Obstacle basket = new Obstacle("Basket", "Climb-on", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(basketArea), new Area.Rectangular(new Coordinate(3351, 2962, 0), new Coordinate(3351, 2962, 0)),
                new Coordinate(3351, 2962, 0), getAbsoluteArea(basketSafeArea), new CustomCamera());

        Obstacle marketStall = new Obstacle("Market stall", "Jump-on", Obstacle.ObstacleType.SLOW,
                new Area.Rectangular(new Coordinate(3346, 2968, 1), new Coordinate(3351, 2964, 1)),
                new Area.Rectangular(new Coordinate(3351, 2974, 2), new Coordinate(3348, 2969, 2)), null, new Area.Rectangular(new Coordinate(3348, 2968, 1), new Coordinate(3351, 2967, 1)),
                        new CustomCamera());

        Obstacle banner = new Obstacle("Banner", "Grab", Obstacle.ObstacleType.SLOW,
                new Area.Rectangular(new Coordinate(3352, 2976, 1), new Coordinate(3355, 2973, 1)),
                new Area.Rectangular(new Coordinate(3355, 2978, 2), new Coordinate(3359, 2977, 2))
                , null, new Area.Rectangular(new Coordinate(3355, 2976, 1), new Coordinate(3353, 2976, 1)), new CustomCamera());

        Obstacle gap = new Obstacle("Gap", "Leap", Obstacle.ObstacleType.INSTANT,
                new Area.Rectangular(new Coordinate(3360, 2979, 1), new Coordinate(3362, 2977, 1)),
                null, null, new Area.Rectangular(new Coordinate(3362, 2977, 1), new Coordinate(3361, 2978, 1)),
                new CustomCamera());

        Obstacle tree = new Obstacle("Tree", "Jump-to", Obstacle.ObstacleType.SLOW,
                new Area.Rectangular(new Coordinate(3366, 2976, 1), new Coordinate(3369, 2974, 1)),
                new Area.Rectangular(new Coordinate(3369, 2980, 2), new Coordinate(3366, 2977, 2)), null, new Area.Rectangular(new Coordinate(3366, 2976, 1), new Coordinate(3369, 2976, 1)),
                new CustomCamera());

        Area[] roughWallArea = {
                new Area.Rectangular(new Coordinate(3365, 2986, 1), new Coordinate(3367, 2982, 1)),
                new Area.Rectangular(new Coordinate(3368, 2984, 1), new Coordinate(3369, 2982, 1))
        };

        Obstacle roughWall = new Obstacle("Rough wall", "Climb", Obstacle.ObstacleType.INSTANT,
                getAbsoluteArea(roughWallArea), null, null, new Area.Rectangular(new Coordinate(3365, 2982, 1), new Coordinate(3367, 2982, 1)),
                new CustomCamera());

        Area[] monkeyBarsArea = {
                new Area.Rectangular(new Coordinate(3365, 2983, 2), new Coordinate(3355, 2983, 2)),
                new Area.Rectangular(new Coordinate(3361, 2982, 2), new Coordinate(3361, 2980, 2)),
                new Area.Rectangular(new Coordinate(3360, 2982, 2), new Coordinate(3355, 2982, 2)),
                new Area.Rectangular(new Coordinate(3355, 2981, 2), new Coordinate(3355, 2980, 2)),
                new Area.Rectangular(new Coordinate(3355, 2984, 2), new Coordinate(3362, 2984, 2)),
                new Area.Rectangular(new Coordinate(3356, 2985, 2), new Coordinate(3359, 2985, 2))
        };

        Obstacle monkeyBars = new Obstacle("Monkeybars", "Cross", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(monkeyBarsArea), new Area.Rectangular(new Coordinate(3358, 2986, 2), new Coordinate(3358, 2990, 2))
                , null, new Area.Rectangular(new Coordinate(3355, 2984, 2), new Coordinate(3360, 2984, 2)),
                new CustomCamera());

        Area[] tree2Area = {
                new Area.Rectangular(new Coordinate(3357, 2995, 2), new Coordinate(3360, 2991, 2)),
                new Area.Rectangular(new Coordinate(3361, 2994, 2), new Coordinate(3362, 2993, 2)),
                new Area.Rectangular(new Coordinate(3363, 2995, 2), new Coordinate(3366, 2990, 2)),
                new Area.Rectangular(new Coordinate(3367, 2992, 2), new Coordinate(3370, 2990, 2))
        };

        Obstacle tree2 = new Obstacle("Tree", "Jump-on", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(tree2Area), new Area.Rectangular(new Coordinate(3356, 2998, 3), new Coordinate(3360, 2996, 3)), null, new Area.Rectangular(new Coordinate(3357, 2995, 2), new Coordinate(3360, 2995, 2)),
                new CustomCamera());

        Area[] dryingLineArea = {
                new Area.Rectangular(new Coordinate(3356, 3004, 2), new Coordinate(3359, 3000, 2)),
                new Area.Rectangular(new Coordinate(3360, 3002, 2), new Coordinate(3362, 3002, 2))
        };

        Area[] dryingLineSafeArea = {
                new Area.Rectangular(new Coordinate(3362, 3002, 2), new Coordinate(3359, 3002, 2)),
                new Area.Rectangular(new Coordinate(3359, 3001, 2), new Coordinate(3359, 3000, 2))
        };

        Obstacle dryingLine = new Obstacle("Drying line", "Jump-to", Obstacle.ObstacleType.SLOW,
                getAbsoluteArea(dryingLineArea), new Area.Rectangular(new Coordinate(3363, 3001, 3), new Coordinate(3365, 2999, 3)),
                null, getAbsoluteArea(dryingLineSafeArea), new CustomCamera());

        return new Course("Pollivineach", 70, basket, marketStall, banner, gap, tree, roughWall, monkeyBars, tree2, dryingLine);
    }


    private Area.Absolute getAbsoluteArea(Area[] areas)
    {
        ArrayList<Coordinate> coordList = new ArrayList<>();
        for(Area area : areas)
        {
            coordList.addAll(area.getCoordinates());
        }
        return new Area.Absolute(coordList);
    }

    public Course getCurrentCourse()
    {
        return currentCourse;
    }
}
