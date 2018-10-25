package org.usfirst.frc.team6325.robot.commands.Auto;

import org.usfirst.frc.team6325.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoChooser 
{
	//public static GamedataFetcher gamedata;
	//public static char switchGamedata = gamedata.switchSide;
	//public static char scaleGamedata = gamedata.scaleSide;
	
	
	
	public enum AutoPosition 
	{
	    LEFT("Left"),
	    MIDDLE("Middle"),
	    RIGHT("Right");

	    private final String name;

	    AutoPosition(String name) 
	    {
	    	this.name = name; 
	    }

	    public String getName() 
	    {
	        return name;
	    }
	}

	public enum AutoPreference 
	{
	    SCALE("Scale"),
	    SWITCH("Switch"),
		SIMPLE("Simple"),
		BASELINE("Baseline");

	    private final String name;

	    AutoPreference(String name)
	    {
	        this.name = name;
	    }

	    public String getName() 
	    {
	        return name;
	    }
	}
	
	public enum AutoCubes 
	{
		ONE("One"), //, new AutoPathSelector.MiddleSwitch.One('L'), new AutoPathSelector.LeftSwitch.One('L'), new AutoPathSelector.RightSwitch.One('L'), new AutoPathSelector.MiddleScale.One('L'), new AutoPathSelector.LeftScale.One('L'), new AutoPathSelector.RightScale.One('L')),
	    TWO("Two"), //, new AutoPathSelector.MiddleSwitch.One('L'), new AutoPathSelector.LeftSwitch.One('L'), new AutoPathSelector.RightSwitch.One('L'), new AutoPathSelector.MiddleScale.One('L'), new AutoPathSelector.LeftScale.One('L'), new AutoPathSelector.RightScale.One('L')),
		THREE("Three"), //, new AutoPathSelector.MiddleSwitch.One('L'), new AutoPathSelector.LeftSwitch.One('L'), new AutoPathSelector.RightSwitch.One('L'), new AutoPathSelector.MiddleScale.One('L'), new AutoPathSelector.LeftScale.One('L'), new AutoPathSelector.RightScale.One('L')),
		ONEONE("OneOne"), //, new AutoPathSelector.MiddleSwitch.One('L'), new AutoPathSelector.LeftSwitch.One('L'), new AutoPathSelector.RightSwitch.One('L'), new AutoPathSelector.MiddleScale.One('L'), new AutoPathSelector.LeftScale.One('L'), new AutoPathSelector.RightScale.One('L')),
		ONETWO("OneTwo"); //, new AutoPathSelector.MiddleSwitch.One('L'), new AutoPathSelector.LeftSwitch.One('L'), new AutoPathSelector.RightSwitch.One('L'), new AutoPathSelector.MiddleScale.One('L'), new AutoPathSelector.LeftScale.One('L'), new AutoPathSelector.RightScale.One('L'));
		
	    //private Command commandMiddleSwitch = new AutoPathSelector.MiddleSwitch.One('L');	
	    //private Command commandLeftSwitch = new AutoPathSelector.LeftSwitch.One('L');;
	    //private Command commandRightSwitch = new AutoPathSelector.RightSwitch.One('L');
	    //private Command commandMiddleScale = new AutoPathSelector.MiddleScale.One('L');
	    //private Command commandLeftScale = new AutoPathSelector.LeftScale.One('L');
	    //private Command commandRightScale = new AutoPathSelector.RightScale.One('L');
	    
	    private final String name;

	    AutoCubes(String name)//, Command CMSwitch, Command CLSwitch, Command CRSwitch, Command CMScale, Command CLScale, Command CRScale)
	    {
	        this.name = name;
	        /*this.commandMiddleSwitch = CMSwitch;
	        this.commandLeftSwitch = CLSwitch;
	        this.commandRightSwitch = CRSwitch;
	        this.commandMiddleScale = CMScale;
	        this.commandLeftScale = CLScale;
	        this.commandRightScale = CRScale;*/

	    }

	    public String getName() 
	    {
	        return name;
	    }
	    	    
	    /*public Command getMiddleSwitchCommand()
	    {
	    	return commandMiddleSwitch;
	    }
	    public Command getLeftSwitchCommand()
	    {
	    	return commandLeftSwitch;
	    }
	    public Command getRightSwitchCommand()
	    {
	    	return commandRightSwitch;
	    }
	    public Command getMiddleScaleCommand()
	    {
	    	return commandMiddleScale;
	    }
	    public Command getLeftScaleCommand()
	    {
	    	return commandLeftScale;
	    }
	    public Command getRightScaleCommand()
	    {
	    	return commandRightScale;
	    }*/

	    /*public char getGamedata()
	    {
	    	return gamedata.switchSide;
	    }*/
	}
}
