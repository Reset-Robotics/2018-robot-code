package org.usfirst.frc.team6325.robot.commands.Auto

import org.usfirst.frc.team6325.robot.commands.Drive.ProfileFollowerUpdate
import org.usfirst.frc.team6325.robot.commands.Intake.*
import org.usfirst.frc.team6325.robot.commands.Lift.*
import org.usfirst.frc.team6325.robot.subsystems.Lift
import org.usfirst.frc.team6325.robot.commands.Auto.GamedataFetcher
import org.sertain.command.CommandGroup

class AutoPathSelector : CommandGroup 
{
	class MiddleSwitch : CommandGroup
	{
		class One : CommandGroup
		{
			fun One(side: Char) 
			{
				System.err.println("MiddleSwitchOneCube")
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.switchSide
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "ab" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "ab" + "_right.csv") })
					//addSequential(by lazy { MoveDistance(0.127, 1, 10000) })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "straight0.5ft" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "straight0.5ft" + "_right.csv") })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "ab" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "ab" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					//addSequential(by lazy { MoveDistance(0.127, 1, 10000) })
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "straight0.5ft" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "straight0.5ft" + "_right.csv") })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}
		
		class Two : CommandGroup
		{
			fun Two(side: Char) 
			{
				System.err.println("MiddleSwitchTwoCube")
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.switchSide
				val lift by lazy { Lift() }
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS1C" +"_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addParallel(by lazy { LiftIntake(-1) })
					addParallel(by lazy { BackIntakeForward(-0.5) })
					lift.moveToPos(300);
					addParallel(by lazy { LiftIntake(0) })
					addParallel(by lazy { BackIntakeForward(0) })
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CNewCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CNewCube" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addParallel(by lazy { ClampOut() })
					addParallel(by lazy { SpinIntakeWheels(-0.5) })
					addParallel(by lazy { LiftIntake(1) })
					lift.moveToPos(14000)
					addParallel(by lazy { ClampIn() })
					addParallel(by lazy { LiftIntake(0) })
					addParallel(by lazy { BackIntakeForward(0) })
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CScoreCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/RightSide/" + "MS2CScoreCube" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS1C" + "_right.csv"))
					addSequential(by lazy { TurnToAngleStupid(0) })
					addParallel(by lazy { LiftIntake(-1) })
					addParallel(by lazy { BackIntakeForward(-0.5) })
					lift.moveToPos(300)
					addParallel(by lazy { LiftIntake(0) })
					addParallel(by lazy { BackIntakeForward(0) })
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CNewCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CNewCube" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addParallel(by lazy { ClampOut() })
					addParallel(by lazy { SpinIntakeWheels(-0.5) })
					addParallel(by lazy { LiftIntake(1) })
					lift.moveToPos(14000)
					addParallel(by lazy { ClampIn() })
					addParallel(by lazy { LiftIntake(0) })
					addParallel(by lazy { BackIntakeForward(0) })
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CScoreCube" + "_left.csv",
		                    "/home/lvuser/MotionProfiles/MiddleSwitchTwoCube/LeftSide/" + "MS2CScoreCube" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}	
		class Three : CommandGroup
		{
			fun Three(side: Char) 
			{
				System.err.println("MiddleSwitchThreeCube")
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.switchSide
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/RightSide/" + "MS1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "MS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleSwitchOneCube/LeftSide/" + "MS1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}
	}
	
	class LeftSwitch : CommandGroup
	{
		class One : CommandGroup
		{
			fun One(side: Char)
			{
				System.err.println("LeftSwitchOneCube")
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.switchSide
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftSwitchOneCube/RightSide/" + "LS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftSwitchOneCube/RightSide/" + "LS1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftSwitchOneCube/LeftSide/" + "LS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftSwitchOneCube/LeftSide/" + "LS1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}	
	}
	
	class RightSwitch : CommandGroup
	{
		class One : CommandGroup
		{
			fun One(side: Char)
			{
				System.err.println("RightSwitchOneCube")
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.switchSide
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightSwitchOneCube/RightSide/" + "RS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightSwitchOneCube/RightSide/" + "RS1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightSwitchOneCube/LeftSide/" + "RS1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightSwitchOneCube/LeftSide/" + "RS1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}	
	}
	
	class MiddleScale : CommandGroup
	{
		class One : CommandGroup
		{
			fun One(side: Char)
			{
				System.err.println("MiddleScaleOneCube")
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.scaleSide
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleScaleOneCube/RightSide/" + "MScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleScaleOneCube/RightSide/" + "MScale1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/MiddleScaleOneCube/LeftSide/" + "MScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/MiddleScaleOneCube/LeftSide/" + "MScale1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}	
	}
	
	class LeftScale : CommandGroup
	{
		class One : CommandGroup
		{
			fun One(side: Char)
			{
				System.err.println("LeftScaleOneCube");
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.scaleSide
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftScaleOneCube/RightSide/" + "RScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/LeftScaleOneCube/RightSide/" + "RScale1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { MoveLift(4000) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					addSequential(by lazy { ClampOut() })
					System.err.println("Left Side Initiated")
					//addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/LeftScaleOneCube/LeftSide/" + "LScale1C" + "_left.csv",
	                //    "/home/lvuser/MotionProfiles/LeftScaleOneCube/LeftSide/" + "LScale1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { MoveLift(7000) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}	
	}
	
	class RightScale : CommandGroup
	{
		class One : CommandGroup
		{
			fun One(side: Char)
			{
				System.err.println("RightScaleOneCube")
				val gamedata by lazy { GamedataFetcher() }
				side = gamedata.scaleSide
				
				if(side == 'R') 
				{
					System.err.println("Right Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightScaleOneCube/RightSide/" + "RScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightScaleOneCube/RightSide/" + "RScale1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				else if(side == 'L')
				{
					System.err.println("Left Side Initiated")
					addSequential(by lazy { ProfileFollowerUpdate("/home/lvuser/MotionProfiles/RightScaleOneCube/LeftSide/" + "RScale1C" + "_left.csv",
	                    "/home/lvuser/MotionProfiles/RightScaleOneCube/LeftSide/" + "RScale1C" + "_right.csv") })
					addSequential(by lazy { TurnToAngleStupid(0) })
					addSequential(by lazy { LiftIntake(-1) })
					addSequential(by lazy { BackIntakeForward(-0.5) })
				}
				System.err.println("Side Run: " + side)
			}
		}	
	}
	
	class Baseline : CommandGroup
	{
		fun Baseline() = addSequential(by lazy { MoveDistance(5, 0.5, 135000) });
	}
}