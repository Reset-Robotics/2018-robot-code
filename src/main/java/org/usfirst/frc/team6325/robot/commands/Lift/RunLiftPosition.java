package org.usfirst.frc.team6325.robot.commands.Lift;

import org.usfirst.frc.team6325.robot.Robot;
import org.usfirst.frc.team6325.robot.subsystems.Lift;
import edu.wpi.first.wpilibj.command.Command;


public class RunLiftPosition extends Command 
{
	private Lift.Positions position;

    public RunLiftPosition(Lift.Positions pos) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
        this.position = pos;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.lift.moveLift(0);
    	Robot.lift.moveToPos(this.position.getPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        if((Math.abs(Robot.lift.getQuadPos(0) - position.getPosition()) < 600) && (Math.abs(Robot.lift.getQuadPos(1) - position.getPosition()) < 600))
        	return true;
        else
        	return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.lift.moveLift(0);
    }
}
