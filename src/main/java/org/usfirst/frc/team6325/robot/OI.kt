package org.usfirst.frc.team6325.robot

import org.sertain.command.*

import edu.wpi.first.wpilibj.command.*

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.Preferences

import org.usfirst.frc.team6325.robot.IDs
import org.usfirst.frc.team6325.robot.commands.Drive.*
//import org.usfirst.frc.team6325.robot.commands.Intake.*
import org.usfirst.frc.team6325.robot.commands.Lift.*

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.buttons.*
import edu.wpi.first.wpilibj.GenericHID


public class OI
{
	val ids: IDs = IDs()
	// Joysticks/Controllers
	val joystickLeft: Joystick = Joystick((ids.joystickLeftIDs.get("USB-ID"))!!)
	val joystickRight: Joystick = Joystick((ids.joystickRightIDs.get("USB-ID"))!!)
	val xboxController: XboxController = XboxController((ids.xboxIDs.get("USB-ID"))!!)
	val xboxJoystickLeft: Joystick = Joystick((ids.xboxIDs.get("Left-Joystick-Y-Axis"))!!)
	val xboxJoystickRight: Joystick = Joystick((ids.xboxIDs.get("Right-Joystick-Y-Axis"))!!)
	// Drivetrain Variables
	var sliderRawAxisNumber: Double = joystickLeft.getRawAxis((ids.joystickLeftIDs.get("SliderAxis"))!!)
	//val drivetrainLeft
	//val drivetrainRight
	val shiftDrivetrain: Button = JoystickButton(joystickLeft, (ids.joystickLeftIDs.get("Trigger"))!!)
	// BackBelt Buttons
	val backBeltsIn: Button = JoystickButton(xboxController, (ids.xboxIDs.get("B-Button"))!!)
	val backBeltsOut: Button = JoystickButton(xboxController, (ids.xboxIDs.get("X-Button"))!!)
	// Intake Buttons
	val toggleIntakePistons: Button = JoystickButton(joystickRight, (ids.joystickRightIDs.get("Trigger"))!!)
	val intakeWheelsForward: Button = JoystickButton(joystickLeft, (ids.joystickLeftIDs.get("Top-Button-Top-Right"))!!)
	val intakeWheelsReverse: Button = JoystickButton(joystickRight, (ids.joystickRightIDs.get("Top-Button-Top-Left"))!!)
	val stopIntakes: Button = JoystickButton(joystickRight, (ids.joystickRightIDs.get("Side-Thumb"))!!)
	val liftWheelsOut: Button = JoystickButton(xboxController, (ids.xboxIDs.get("A-Button"))!!)
	val liftWheelsIn: Button = JoystickButton(xboxController, (ids.xboxIDs.get("Y-Button"))!!)


	public fun OI()
	{
    	//none of these commands exist yet

	    //shiftDrivetrain.whenPressed(ShiftTransmission())
	    //backBeltsIn.whenPressed(BackIntakeForward(0))
	    //toggleIntakePistons.whenPressed(ToggleClamp())

	    //intakeWheelsReverse.whileHeld(SpinIntakeWheels(-1))
		//intakeWheelsReverse.whileHeld(LiftIntake(1))
		//intakeWheelsReverse.whenReleased(LiftIntake(0))

		//stopIntakes.whenPressed(StopIntakeWheels())
		//stopIntakes.whenPressed(LiftIntake(0))

		//intakeWheelsForward.whileHeld(SpinIntakeWheels(1))
		//intakeWheelsForward.whileHeld(LiftOuttake())

		//liftWheelsOut.whileHeld(LiftOuttake())
		//liftWheelsOut.whenReleased(LiftIntake(0))
	
		//liftWheelsIn.whileHeld(LiftIntake(-1))
		//liftWheelsIn.whenReleased(LiftIntake(0))
		
		//backBeltsIn.whileHeld(BackIntakeForward(0.5))
		//backBeltsOut.whileHeld(BackIntakeForward(-0.5))
	}
}