package com.techhounds.arm;

import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Represents the pneumatic arms that squeeze the box.
 */
public class Arm extends Subsystem implements DashboardUpdatable {
	
	public static final boolean DEBUG = false;
	
	private Solenoid arm;
	
	public Arm(){
		arm = new Solenoid(RobotMap.ARMS);
	}
	
    public void toggleArm(){
    	arm.set(!arm.get());
    }
    
    public void openArm(){
    	arm.set(true);
    }
    
    public void closeArm(){
    	arm.set(false);
    }
    
    public void setArm(boolean open) {
    	arm.set(open);
    }
    
    public boolean isOpen() {
    	return arm.get();
    }

    /**
     * Intentionally left blank
     */
    public void initDefaultCommand() {
    }

	@Override
	public void initSD() {
		
		if (DEBUG) {
			SmartDashboard.putData(this);
			SmartDashboard.putData("Toggle Arms", new ToggleArm());
			SmartDashboard.putData("Open Arms", new SetArm(true));
			SmartDashboard.putData("Close Arms", new SetArm(false));
		}
	}

	@Override
	public void updateSD() {
		SmartDashboard.putBoolean("Arm Actuator State", arm.get());
	}
}
