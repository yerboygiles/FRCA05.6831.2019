package org.usfirst.frc.team6831.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class sweeper extends subsystem{
	
	
	public void update() {
		if(stick1.getRawButton(5)) {
			sweeper.set(1);
		}else if(stick1.getRawButton(3)) {
			sweeper.set(-.4);
		}else {
			sweeper.set(0);
		}
	}
	
	
	
	
	int port;
	Joystick stick1;
	WPI_TalonSRX sweeper;
	
	public sweeper(Joystick stick1, int port) {
		this.stick1 = stick1;
		this.port = port;
	}

	public void init() {
		sweeper = new WPI_TalonSRX(port);
		sweeper.set(ControlMode.PercentOutput, 0);
		sweeper.setInverted(false);
	}
}
