package org.usfirst.frc.team6325.robot;

import org.usfirst.frc.team6325.robot.commands.Drive.*;
import org.usfirst.frc.team6325.robot.commands.Intake.*;
import org.usfirst.frc.team6325.robot.commands.Lift.*;
import org.usfirst.frc.team6325.robot.subsystems.Lift.Positions;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;

import java.util.*;


public class IDConfig 
{
    public Hashtable<String, Integer> joystickLeftIDs = new Hashtable<String, Integer>();
	public Hashtable<String, Integer> joystickRightIDs = new Hashtable<String, Integer>();
	public Hashtable<String, Integer> xboxIDs = new Hashtable<String, Integer>();
	   
    
    public IDConfig () 
    {
		// Left Joystick Config
		joystickLeftIDs.put("USB-ID", 0);
		joystickLeftIDs.put("X-Axis", 0);
		joystickLeftIDs.put("Y-Axis", 1);
		joystickLeftIDs.put("Z-Axis", 2);
		joystickLeftIDs.put("Trigger", 1);
		joystickLeftIDs.put("Side-Thumb", 2);
		joystickLeftIDs.put("Top-Button-Bottom-Right", 3);
		joystickLeftIDs.put("Top-Button-Bottom-Left", 4);
		joystickLeftIDs.put("Top-Button-Top-Left", 5);
		joystickLeftIDs.put("Top-Button-Top-Right", 6);

		// Right Joystick Config
		joystickRightIDs.put("USB-ID", 1);
		joystickRightIDs.put("X-Axis", 0);
		joystickRightIDs.put("Y-Axis", 1);
		joystickRightIDs.put("Z-Axis", 2);
		joystickRightIDs.put("Trigger", 1);
		joystickRightIDs.put("Side-Thumb", 2);
		joystickRightIDs.put("Top-Button-Bottom-Right", 3);
		joystickRightIDs.put("Top-Button-Bottom-Left", 4);
		joystickRightIDs.put("Top-Button-Top-Left", 5);
		joystickRightIDs.put("Top-Button-Top-Right", 6);
		
		// Xbox Controller Config
		xboxIDs.put("USB-ID", 2);
		xboxIDs.put("A-Button", 1);
		xboxIDs.put("B-Button", 2);
		xboxIDs.put("X-Button", 3);
		xboxIDs.put("Y-Button", 4);
		xboxIDs.put("Left-Bumper", 5);
		xboxIDs.put("Right-Bumper", 6);
		xboxIDs.put("Back-Button", 7);
		xboxIDs.put("Left-Joystick-Button", 8);
		xboxIDs.put("Right-Joystick-Button", 9);   
	}
}
