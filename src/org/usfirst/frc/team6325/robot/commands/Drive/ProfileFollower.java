package org.usfirst.frc.team6325.robot.commands.Drive;

import org.usfirst.frc.team6325.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class ProfileFollower extends Command {
	String leftCSV, rightCSV;
	public ProfileFollower(String leftCSVFile, String rightCSVFile) {
		leftCSV = leftCSVFile;
		rightCSV = rightCSVFile;
	}
	@Override
	protected void initialize() {
		Robot.drivetrain.initPath(leftCSV, rightCSV);
	}
	@Override
	protected void execute() {
		Robot.drivetrain.executePath();
		
	    }
	@Override
    protected void end() {
        super.end();
        Robot.drivetrain.drive(0, 0);
    }
	
	@Override
	protected void interrupted() {
	    end();
	}


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
