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
	public fun OI()
	{
		val ids: IDs by lazy { IDs() }

		val joystickLeft: Joystick by lazy { Joystick(ids.joystickLeftIDs.get("USB-ID")) }
		val joystickRight: Joystick by lazy { Joystick(ids.joystickRightIDs.get("USB-ID")) }
		val xboxController: XboxController by lazy { XboxController(ids.xboxIDs.get("USB-ID")) }
		val xboxJoystickLeft: Joystick by lazy { Joystick(ids.xboxIDs.get("Left-Joystick-Y-Axis")) }
		val xboxJoystickRight: Joystick by lazy { Joystick(ids.xboxIDs.get("Right-Joystick-Y-Axis")) }
		val sliderRawAxisNumber: Int = Polybius.oi.joystickLeft.getRawAxis(ids.joystickLeftIDs.get("SliderAxis"))
		//val drivetrainLeft
		//val drivetrainRight
		val shiftDrivetrain: Button
		val backBeltsIn: Button
		val backBeltsOut: Button
		val toggleIntakePistons: Button
		val intakeWheelsForward: Button
		val intakeWheelsReverse: Button
		val stopIntakes: Button
		val liftWheelsOut: Button
		val liftWheelsIn: Button

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