package org.usfirst.frc.team6325.robot.commands.Lift

import org.sertain.command.*
import org.usfirst.frc.team6325.robot.Polybius
import org.usfirst.frc.team6325.robot.IDs


class JoystickBackIntake : Command()
{
    fun JoystickBackIntake() = requires(Polybius.backBelts)

    override fun execute() = Polybius.backBelts.moveBackBelts(-Polybius.oi.xboxController.getRawAxis(IDs.xboxIDs.get("Right-Joystick-Y-Axis")))

    override fun isCompleted() = return false
}