package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollower;
import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollowerUpdate;
import org.usfirst.frc.team6325.robot.commands.Lift.BackIntakeForward;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftIntake;
import org.usfirst.frc.team6325.robot.commands.Lift.LiftOuttake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPathSelector extends CommandGroup 
{
	public static class MiddleSwitch extends CommandGroup
	{
		public static class One extends CommandGroup
		{
			public One(char side) 
			{
				System.err.println("MiddleSwitchOneCube");

				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MidRightSwitch/MidRightSwitch_left_detailed.csv",
	                    "/home/lvuser/MotionProfiles/MidRightSwitch/MidRightSwitch_right_detailed.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MidLeftSwitch/MidLeftSwitch_left_detailed.csv",
	                    "/home/lvuser/MotionProfiles/MidLeftSwitch/MidLeftSwitch_right_detailed.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}	
		// public class TwoCube
		// public class ThreeCube
	}
	
	public static class LeftSwitch extends CommandGroup
	{
		public static class One extends CommandGroup
		{
			public One(char side)
			{
				System.err.println("LeftSwitchOneCube");

				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftSwitchOneCube/RightSide/LS1C_RightSide_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftSwitchOneCube/RightSide/LS1C_RightSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftSwitchOneCube/LeftSide/LS1C_LeftSide_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftSwitchOneCube/LeftSide/LS1C_LeftSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}	
	}
	
	public static class RightSwitch extends CommandGroup
	{
		public static class One extends CommandGroup
		{
			public One(char side)
			{
				System.err.println("RightSwitchOneCube");

				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightSwitchOneCube/RightSide/RS1C_RightSide_left.csv",
	                    "/home/lvuser/MotionProfiles/RightSwitchOneCube/RightSide/RS1C_RightSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightSwitchOneCube/LeftSide/RS1C_LeftSide_left.csv",
	                    "/home/lvuser/MotionProfiles/RightSwitchOneCube/LeftSide/RS1C_LeftSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}	
	}
	
	public static class MiddleScale extends CommandGroup
	{
		public static class One extends CommandGroup
		{
			public One(char side)
			{
				System.err.println("MiddleScaleOneCube");

				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleScaleOneCube/RightSide/MS1C_RightSide_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleScaleOneCube/RightSide/MS1C_RightSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleScaleOneCube/LeftSide/MS1C_LeftSide_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleScaleOneCube/LeftSide/MS1C_LeftSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}	
	}
	
	public static class LeftScale extends CommandGroup
	{
		public static class One extends CommandGroup
		{
			public One(char side)
			{
				System.err.println("LeftScaleOneCube");

				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftScaleOneCube/RightSide/LS1C_RightSide_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftScaleOneCube/RightSide/LS1C_RightSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftScaleOneCube/LeftSide/LS1C_LeftSide_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftScaleOneCube/LeftSide/LS1C_LeftSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}	
	}
	
	public static class RightScale extends CommandGroup
	{
		public static class One extends CommandGroup
		{
			public One(char side)
			{
				System.err.println("RightScaleOneCube");

				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightScaleOneCube/RightSide/RS1C_RightSide_left.csv",
	                    "/home/lvuser/MotionProfiles/RightScaleOneCube/RightSide/RS1C_RightSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightScaleOneCube/LeftSide/RS1C_LeftSide_left.csv",
	                    "/home/lvuser/MotionProfiles/RightScaleOneCube/LeftSide/RS1C_LeftSide_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}	
	}
	
	public static class Baseline extends CommandGroup
	{
		public Baseline() 
		{
			addSequential(new MoveDistance(5, 0.5, 100000));
		}
	}
}