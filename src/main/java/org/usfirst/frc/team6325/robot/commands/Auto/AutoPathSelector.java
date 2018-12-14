package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollowerUpdate;
import org.usfirst.frc.team6325.robot.commands.Intake.*;
import org.usfirst.frc.team6325.robot.commands.Lift.*;
import org.usfirst.frc.team6325.robot.subsystems.Lift;
import org.usfirst.frc.team6325.robot.commands.Auto.GamedataFetcher;
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
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.switchSide;
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "ab" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "ab" + "_right.csv"));
					//addSequential(new MoveDistance(0.127, 1, 10000));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "straight0.5ft" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "straight0.5ft" + "_right.csv"));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "ab" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "ab" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					//addSequential(new MoveDistance(0.127, 1, 10000));
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "straight0.5ft" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "straight0.5ft" + "_right.csv"));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}
		
		public static class Two extends CommandGroup
		{
			public Two(char side) 
			{
				System.err.println("MiddleSwitchTwoCube");
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.switchSide;
				Lift lift = new Lift();
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS1C" +"_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addParallel(new LiftIntake(-1));
					addParallel(new BackIntakeForward(-0.5));
					lift.moveToPos(300);
					addParallel(new LiftIntake(0));
					addParallel(new BackIntakeForward(0));
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CNewCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CNewCube" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addParallel(new ClampOut());
					addParallel(new SpinIntakeWheels(-0.5));
					addParallel(new LiftIntake(1));
					lift.moveToPos(14000);
					addParallel(new ClampIn());
					addParallel(new LiftIntake(0));
					addParallel(new BackIntakeForward(0));
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CScoreCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CScoreCube" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addParallel(new LiftIntake(-1));
					addParallel(new BackIntakeForward(-0.5));
					lift.moveToPos(300);
					addParallel(new LiftIntake(0));
					addParallel(new BackIntakeForward(0));
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CNewCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CNewCube" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addParallel(new ClampOut());
					addParallel(new SpinIntakeWheels(-0.5));
					addParallel(new LiftIntake(1));
					lift.moveToPos(14000);
					addParallel(new ClampIn());
					addParallel(new LiftIntake(0));
					addParallel(new BackIntakeForward(0));
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CScoreCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CScoreCube" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}	
		public static class Three extends CommandGroup
		{
			public Three(char side) 
			{
				System.err.println("MiddleSwitchThreeCube");
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.switchSide;
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "MS1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "MS1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				System.err.println("Side Run: " + side);
			}
		}
	}
	
	public static class LeftSwitch extends CommandGroup
	{
		public static class One extends CommandGroup
		{
			public One(char side)
			{
				System.err.println("LeftSwitchOneCube");
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.switchSide;
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftSwitchOneCube/RightSide/" + "LS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftSwitchOneCube/RightSide/" + "LS1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftSwitchOneCube/LeftSide/" + "LS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftSwitchOneCube/LeftSide/" + "LS1C" + "_right.csv"));
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
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.switchSide;
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightSwitchOneCube/RightSide/" + "RS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightSwitchOneCube/RightSide/" + "RS1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightSwitchOneCube/LeftSide/" + "RS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightSwitchOneCube/LeftSide/" + "RS1C" + "_right.csv"));
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
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.scaleSide;
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleScaleOneCube/RightSide/" + "MScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleScaleOneCube/RightSide/" + "MScale1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleScaleOneCube/LeftSide/" + "MScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleScaleOneCube/LeftSide/" + "MScale1C" + "_right.csv"));
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
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.scaleSide;
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftScaleOneCube/RightSide/" + "RScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftScaleOneCube/RightSide/" + "RScale1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new MoveLift(4000));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					addSequential(new ClampOut());
					System.err.println("Left Side Initiated");
					//addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftScaleOneCube/LeftSide/" + "LScale1C" + "_left.csv",
	                //    "/home/lvuser/MotionProfiles/LeftScaleOneCube/LeftSide/" + "LScale1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new MoveLift(7000));
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
				GamedataFetcher gamedata = new GamedataFetcher();
				side = gamedata.scaleSide;
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightScaleOneCube/RightSide/" + "RScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightScaleOneCube/RightSide/" + "RScale1C" + "_right.csv"));
					addSequential(new TurnToAngleStupid(0));
					addSequential(new LiftIntake(-1));
					addSequential(new BackIntakeForward(-0.5));
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated");
					addSequential(new ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightScaleOneCube/LeftSide/" + "RScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightScaleOneCube/LeftSide/" + "RScale1C" + "_right.csv"));
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
			addSequential(new MoveDistance(5, 0.5, 135000));
		}
	}
}