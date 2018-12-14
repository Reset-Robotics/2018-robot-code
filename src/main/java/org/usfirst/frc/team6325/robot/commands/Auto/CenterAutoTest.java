package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Paths.Center;
import org.usfirst.frc.team6325.robot.commands.Drive.WaypointFollower;
import org.usfirst.frc.team6325.robot.commands.Lift.BackIntakeForward;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class CenterAutoTest extends CommandGroup 
{
	Command driveToSwitch = new WaypointFollower(Center.toLeftSwitch);

    public CenterAutoTest() 
    {
        addSequential(driveToSwitch);
    }
}