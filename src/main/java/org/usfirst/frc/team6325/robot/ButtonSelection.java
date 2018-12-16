package org.usfirst.frc.team6325.robot;

import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc.team6325.robot.IDConfig;
import org.usfirst.frc.team6325.robot.OI;

import java.util.*;


public class ButtonSelection 
{
    IDConfig ids = new IDConfig();
    OI oi = new OI();
    
    public Hashtable<String, Button> competitionButtons = new Hashtable<String, Button>();
    public Hashtable<String, Double> competitionDrivetrainAxes = new Hashtable<String, Double>();
    public Hashtable<String, Button> paradeButtons = new Hashtable<String, Button>();
    public Hashtable<String, Double> paradeDrivetrainAxes = new Hashtable<String, Double>();
    public Hashtable<String, Button> outreachButtons = new Hashtable<String, Button>();
    public Hashtable<String, Double> outreachDrivetrainAxes = new Hashtable<String, Double>();
    
    public enum ButtonMapMode
    {
        COMPETITION("competition"),
	    PARADE("parade"),
	    OUTREACH("outreach");

	    private final String name;

	    ButtonMapMode(String name){ this.name = name; }

	    public String getName(){ return name; }
    }

    public ButtonSelection() 
    {
        competitionButtons.put("shiftDrivetrain", new JoystickButton(oi.joystickLeft, ids.joystickLeftIDs.get("Trigger")));
        competitionButtons.put("backBeltsIn", new JoystickButton(oi.xboxController, ids.xboxIDs.get("B-Button")));
        competitionButtons.put("backBeltsOut", new JoystickButton(oi.xboxController, ids.xboxIDs.get("X-Button")));
        competitionButtons.put("toggleIntakePistons", new JoystickButton(oi.joystickRight, ids.joystickRightIDs.get("Trigger")));
        competitionButtons.put("intakeWheelsForward", new JoystickButton(oi.joystickLeft, ids.joystickLeftIDs.get("Top-Button-Top-Right")));
        competitionButtons.put("IntakeWheelsReverse" , new JoystickButton(oi.joystickRight, ids.joystickRightIDs.get("Top-Button-Top-Left")));
        competitionButtons.put("stopIntakes", new JoystickButton(oi.joystickRight, ids.joystickRightIDs.get("Side-Thumb")));
        competitionButtons.put("liftWheelsOut", new JoystickButton(oi.xboxController, ids.xboxIDs.get("A-Button")));
        competitionButtons.put("liftWheelsIn", new JoystickButton(oi.xboxController, ids.xboxIDs.get("Y-Button")));
        competitionDrivetrainAxes.put("Tank-Left-Joystick-Drive", -Robot.oi.joystickLeft.getY());
        competitionDrivetrainAxes.put("Tank-Right-Joystick-Drive", -Robot.oi.joystickRight.getY());
        competitionDrivetrainAxes.put("Arcade-Joystick-Drive-Y", Robot.oi.joystickLeft.getY());
        competitionDrivetrainAxes.put("Arcade-Joystick-Drive-X", Robot.oi.joystickLeft.getX());

        paradeButtons.put("shiftDrivetrain", new JoystickButton(oi.xboxController, ids.xboxIDs.get("Right-Bumper")));
        paradeDrivetrainAxes.put("Tank-Left-Joystick-Drive", Robot.oi.xboxJoystickLeft.getY());
        paradeDrivetrainAxes.put("Tank-Right-Joystick-Drive", Robot.oi.xboxJoystickRight.getY());
        paradeDrivetrainAxes.put("Arcade-Joystick-Drive-Y", Robot.oi.xboxJoystickLeft.getY());
        paradeDrivetrainAxes.put("Arcade-Joystick-Drive-X", Robot.oi.xboxJoystickLeft.getX());
	}
}
