package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Lift.BackIntakeForward;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntake;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntakeEject;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Baseline extends CommandGroup {

	public Baseline() {
		addSequential(new MoveDistance(5, 0.5, 100000));
	}
	
}
