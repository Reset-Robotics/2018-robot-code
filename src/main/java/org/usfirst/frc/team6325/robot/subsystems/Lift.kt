package org.usfirst.frc.team6325.robot.subsystems

import org.sertain.command.Subsystem
import org.usfirst.frc.team6325.robot.IDs
import org.usfirst.frc.team6325.robot.commands.Lift.JoystickLift
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode

object Lift : Subsystem()
{
    val leftMaster by lazy { WPI_TalonSRX(IDs.liftMotorIDs.get("Left-Master")) }
    val right by lazy { WPI_TalonSRX(IDs.liftMotorIDs.get("Right-Master")) }
    private val CRUISE_VELOCITY = 19000
    private val CRUISE_ACCELERATION = 11000
    private val CRUISE_VELOCITY_DOWN = CRUISE_VELOCITY * 0.7
    private val CRUISE_ACCELERATION_DOWN = CRUISE_ACCELERATION * 0.6

    
    
}