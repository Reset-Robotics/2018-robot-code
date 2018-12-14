package org.usfirst.frc.team6325.robot.commands.Intake;

import org.usfirst.frc.team6325.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class ToggleClamp extends Command 
{
    public ToggleClamp() 
    {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.intake);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.intake.toggleClamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return true;
    }
}
