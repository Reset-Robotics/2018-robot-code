package org.usfirst.frc.team6325.robot.Paths;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class Center {

	public static Waypoint[] toLeftSwitch = new Waypoint[]{
            new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
            new Waypoint(8.2, 3.3, Pathfinder.d2r(0.0)),
    };
	
	public static Waypoint[] toRightSwitch = new Waypoint[]{
            new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
            new Waypoint(8.2, -3.3, Pathfinder.d2r(0.0)),
    };
	
}


