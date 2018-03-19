package org.usfirst.frc.team6325.robot.commands.Auto;

public class AutoChooser {
	public enum AutoPosition {
	    LEFT("Left"),
	    MIDDLE("Middle"),
	    RIGHT("Right");

	    private final String name;

	    AutoPosition(String name) {
	    	this.name = name; 
	    }

	    public String getName() {
	        return name;
	    }
	}

	public enum AutoPreference {
	    SCALE("Scale"),
	    SWITCH("Switch"),
		SIMPLE("Simple");

	    private final String name;

	    AutoPreference(String name){
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }


	}
}
