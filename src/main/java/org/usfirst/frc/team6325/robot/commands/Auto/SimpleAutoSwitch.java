package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntake;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class SimpleAutoSwitch extends CommandGroup 
{

	public SimpleAutoSwitch(char switchSide) 
	{
		if(switchSide == 'R') 
		{
			addSequential(new MoveDistance(2, 0.5, 100000));
			addSequential(new TurnToAngleStupid(40));
			addSequential(new MoveDistance(4, 0.5, 100000));
			addSequential(new TurnToAngleStupid(10));
			addSequential(new MoveDistance(2.5, 0.3, 3000));
			addSequential(new LiftIntake(-1));
		} else if(switchSide =='L') 
		{
			addSequential(new MoveDistance(2, 0.5, 100000));
			addSequential(new TurnToAngleStupid(-40));
			addSequential(new MoveDistance(4.5, 0.5, 100000));
			addSequential(new TurnToAngleStupid(-10));
			addSequential(new MoveDistance(2.5, 0.3, 3000));
			addSequential(new LiftIntake(-1));
		}
	}
}
