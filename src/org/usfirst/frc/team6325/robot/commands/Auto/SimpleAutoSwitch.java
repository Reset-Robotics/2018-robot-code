package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Lift.BackIntakeForward;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntakeEject;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SimpleAutoSwitch extends CommandGroup {

	public SimpleAutoSwitch() {
		addSequential(new MoveDistance(0.5, 0.5));
		addSequential(new TurnToAngle(45));
		addSequential(new MoveDistance(1, 0.5));
		addSequential(new TurnToAngle(0));
		addSequential(new MoveDistance(2, 0.5));
		addParallel(new BackIntakeForward(0.5));
		addSequential(new LiftIntakeEject());
		
		
	}
}
