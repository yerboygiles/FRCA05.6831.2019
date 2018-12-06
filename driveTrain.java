package org.usfirst.frc.team6831.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



public class driveTrain {
	
	int portr1, portr2, portr3, portl1, portl2, portl3, ports1, ports2;
	public WPI_TalonSRX motorRight;
	public WPI_TalonSRX motorRightFollower1;
	public WPI_TalonSRX motorRightFollower2;
	public WPI_TalonSRX motorLeftFollower2;
	public WPI_TalonSRX motorLeftFollower1;
	public WPI_TalonSRX motorLeft;
	
	public driveTrain(int portr1, int portr2, int portr3, int portl1, int portl2, int portl3, int ports1, int ports2) {
		this.portr1 = portr1;
		this.portr2 = portr2;
		this.portr3 = portr3;
		this.portl1 = portl1;
		this.portl2 = portl2;
		this.portl3 = portl3;
		this.ports1 = ports1;
		this.ports2 = ports2;
	}
	

	
	public void create() {
		motorRight = new WPI_TalonSRX(portr1);
		motorRightFollower1 = new WPI_TalonSRX(portr2);
		motorRightFollower2 = new WPI_TalonSRX(portr3);
		motorLeftFollower2 = new WPI_TalonSRX(portl3);
		motorLeftFollower1 = new WPI_TalonSRX(portl2);
		motorLeft = new WPI_TalonSRX(portl1);
	}
	
	public void init() {
		motorRight.set(ControlMode.PercentOutput, 0);
		motorRightFollower1.follow(motorRight);
		motorRightFollower2.follow(motorRight);
		
		motorLeft.set(ControlMode.PercentOutput, 0);
		motorLeft.setInverted(true);
		motorLeftFollower1.follow(motorLeft);
		motorLeftFollower1.setInverted(true);
		motorLeftFollower2.follow(motorLeft);
		motorLeftFollower2.setInverted(true);
	}

}
