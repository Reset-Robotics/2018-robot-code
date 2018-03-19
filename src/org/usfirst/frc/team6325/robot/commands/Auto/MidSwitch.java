package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollower;
import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollowerUpdate;
import org.usfirst.frc.team6325.robot.commands.Lift.BackIntakeForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidSwitch extends CommandGroup {

    public MidSwitch(char side) {
        System.err.println("MidSwitch.");

        if(side == 'R') {
        	System.err.println("right side initiated");
            addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MidRightSwitch/MidRightSwitch_left_detailed.csv",
                    "/home/lvuser/MotionProfiles/MidRightSwitch/MidRightSwitch_right_detailed.csv"));
            addSequential(new BackIntakeForward(-0.5));
        }
        else if(side == 'L'){
        	System.err.println("left side initiated");
            addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MidLeftSwitch/MidLeftSwitch_left_detailed.csv",
                    "/home/lvuser/MotionProfiles/MidLeftSwitch/MidLeftSwitch_right_detailed.csv"));
            addSequential(new BackIntakeForward(-0.5));
        }
        System.err.println("Side ran " + side);
    }
}