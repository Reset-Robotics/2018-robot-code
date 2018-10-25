package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class AutoSwitchTurn extends Command {

	double ang = 0.0;
	boolean done = false;
	
	public AutoSwitchTurn() {
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
                if(gameData.length() > 0)
                {
		  if(gameData.charAt(0) == 'L')
		  {
			ang = -45;
		  } else {
			ang = 45;
		  }
                }
    	Robot.drivetrain.autoTurnInit(ang);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.autoTurn();
    	
    	if(Math.abs(Robot.drivetrain.getAngle() - ang) <= 5) {
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.autoTurnStop();
    	Robot.drivetrain.killMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.autoTurnStop();
    	Robot.drivetrain.killMotors();
    }

}
