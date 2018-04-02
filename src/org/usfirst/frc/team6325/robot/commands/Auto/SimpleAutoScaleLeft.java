package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Lift.BackIntakeForward;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntake;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntakeEject;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SimpleAutoScaleLeft extends CommandGroup {

	public SimpleAutoScaleLeft(char switchSide) {
		if(switchSide == 'L') {
			addSequential(new MoveDistance(5, 0.5, 100000));
			addSequential(new TurnToAngleStupid(15));
			addSequential(new MoveDistance(4, 0.5, 100000));
			//RaiseLift
			addSequential(new LiftIntake(-1));
		} else if(switchSide =='R') {
			addSequential(new MoveDistance(5, 0.5, 100000));
			addSequential(new TurnToAngleStupid(90));
			addSequential(new MoveDistance(4.5, 0.5, 100000));
			addSequential(new TurnToAngleStupid(-100));
			// Raise lift
			addSequential(new LiftIntake(-1));
		}
	}
}
