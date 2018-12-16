package org.usfirst.frc.team6325.robot;


class IDs()
{
    fun IDs()
    {
        // Driver Controls/OI

        val joystickLeftIDs = HashMap<String, Int>() // holds ids for different buttons and axes on the left driver joystick
        joystickLeftIDs.put("USB-ID", 0)
        joystickLeftIDs.put("X-Axis", 0)
        joystickLeftIDs.put("Y-Axis", 1)
        joystickLeftIDs.put("Z-Axis", 2)
        joystickLeftIDs.put("Trigger", 1)
        joystickLeftIDs.put("Side-Thumb", 2)
        joystickLeftIDs.put("Top-Button-Bottom-Right", 3)
        joystickLeftIDs.put("Top-Button-Bottom-Left", 4)
        joystickLeftIDs.put("Top-Button-Top-Left", 5)
        joystickLeftIDs.put("Top-Button-Top-Right", 6)
        joystickLeftIDs.put("SliderAxis", 3)

        val joystickRightIDs = HashMap<String, Int>() // holds ids for different buttons and axes on the right driver joystick
        joystickRightIDs.put("USB-ID", 1)
        joystickRightIDs.put("X-Axis", 0)
        joystickRightIDs.put("Y-Axis", 1)
        joystickRightIDs.put("Z-Axis", 2)
        joystickRightIDs.put("Trigger", 1)
        joystickRightIDs.put("Side-Thumb", 2)
        joystickRightIDs.put("Top-Button-Bottom-Right", 3)
        joystickRightIDs.put("Top-Button-Bottom-Left", 4)
        joystickRightIDs.put("Top-Button-Top-Left", 5)
        joystickRightIDs.put("Top-Button-Top-Right", 6)

        val xboxIDs = HashMap<String, Int>() // holds ids for different buttons and axes on the xbox controller
        xboxIDs.put("USB-ID", 2)
        xboxIDs.put("A-Button", 1)
        xboxIDs.put("B-Button", 2)
        xboxIDs.put("X-Button", 3)
        xboxIDs.put("Y-Button", 4)
        xboxIDs.put("Left-Bumper", 5)
        xboxIDs.put("Right-Bumper", 6)
        xboxIDs.put("Back-Button", 7)
        xboxIDs.put("Left-Joystick-Button", 8)
        xboxIDs.put("Right-Joystick-Button", 9) 
        xboxIDs.put("Left-Joystick-Y-Axis", 1)
        xboxIDs.put("Right-Joystick-Y-Axis", 5)


        // Motor Controllers

        val driveMotorIDs = HashMap<String, Int>() // holds all our talon ids for drive motors
        driveMotorIDs.put("Right-Master", 2)
        driveMotorIDs.put("Right-Front", 7)
        driveMotorIDs.put("Right-Back", 9)
        driveMotorIDs.put("Left-Master", 3)
        driveMotorIDs.put("Left-Front", 4)
        driveMotorIDs.put("Left-Back", 8)

        val liftMotorIDs = HashMap<String, Int>() // holds all our motor controller ids for lift motors
        liftMotorIDs.put("Left-Master", 0)
        liftMotorIDs.put("Right-Master", 1)

        val pwmMotorIDs = HashMap<String, Int>() // holds all our PWM ids
        pwmMotorIDs.put("Back-Belts", 0)
        pwmMotorIDs.put("Right-Intake-Wheel", 1)
        pwmMotorIDs.put("Left-Intake-Wheel", 2)
        pwmMotorIDs.put("Lift-Intake-Wheels", 3)


       // Pneumatics

        val shifterSolenoidPorts = HashMap<String, Int>() // holds our solenoid ports on the PCM for our drivetrain shifter
        shifterSolenoidPorts.put("Left-Port", 0)
        shifterSolenoidPorts.put("Right-Port", 1)

        val intakeSolenoidPorts = HashMap<String, Int>() // holds our solenoid ports on the PCM for our intake pistons
        intakeSolenoidPorts.put("Left-Port", 2)
        intakeSolenoidPorts.put("Right-Port", 3)
    }
}