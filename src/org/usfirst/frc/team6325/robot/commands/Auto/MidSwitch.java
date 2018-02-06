package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollower;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidSwitch extends CommandGroup {

    public MidSwitch(char side) {
        System.err.println("MidSwitch.");

        if(side == 'R') {
            addSequential(new ProfileFollower("/home/lvuser/MotionProfiles/MidRightSwitch/MidLeftSwitch_left_detailed.csv",
                    "/home/lvuser/MotionProfiles/MidRightSwitch/MidLeftSwitch_right_detailed.csv"));
        }
        else if(side == 'L'){
            addSequential(new ProfileFollower("/home/lvuser/MotionProfiles/MidLeftSwitch/MidRightSwitch_left_detailed.csv",
                    "/home/lvuser/MotionProfiles/MidLeftSwitch/MidRightSwitch_right_detailed.csv"));
        }
    }
}