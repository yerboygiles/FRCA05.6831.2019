package org.usfirst.frc.team6831.robot.commands;

import org.usfirst.frc.team6831.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class encoderDrive extends command{
	
	public boolean init(Robot robot) {
		SmartDashboard.putString("DB/String 6", "init");
		return true;
	}

	public boolean update(Robot robot){
		SmartDashboard.putString("DB/String 7", "running");
		return true;
	}
	
	public boolean resolve(Robot robot) {
		SmartDashboard.putString("DB/String 8", "done");
		return true;
	}
	
	
}
