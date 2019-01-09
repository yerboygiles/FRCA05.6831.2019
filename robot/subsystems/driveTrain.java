package org.usfirst.frc.team6831.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class driveTrain extends subsystem{
	
	public void update() {
		SmartDashboard.putString("DB/String 2", Double.toString(stick1.getRawAxis(1)));
		SmartDashboard.putString("DB/String 5", "functional");
		
		//updateGyro();
		
		switch(gear) {
		case LOWGEAR:
			SmartDashboard.putString("DB/String 4", Double.toString(motorLeft.getSensorCollection().getQuadraturePosition()));
			SmartDashboard.putString("DB/String 3", "Low");
			if(Math.abs(stick1.getRawAxis(1)) > .4) {
				gear = State.UPSHIFTING;
			}
			motorRight.set((stick1.getRawAxis(1)+(stick1.getRawAxis(2)*.75)/2)*2.174);
			motorLeft.set((stick1.getRawAxis(1)-(stick1.getRawAxis(2)*.75)/2)*2.174);
			break;
		case HIGHGEAR:
			SmartDashboard.putString("DB/String 3", "high");
			if(Math.abs(stick1.getRawAxis(1)) < .3) {
				gear = State.DOWNSHIFTING;
			}
			motorRight.set(stick1.getRawAxis(1)+(stick1.getRawAxis(2)*.75)/2);
			motorLeft.set(stick1.getRawAxis(1)-(stick1.getRawAxis(2)*.75)/2);
			break;
		case UPSHIFTING:
			SmartDashboard.putString("DB/String 3", "sUp");
			upshift();
			break;
		case DOWNSHIFTING:
			SmartDashboard.putString("DB/String 3", "sDown");
			downshift();
			break;
		}
		
	}
	
	
	int shiftstate = 0;
	State gear = State.LOWGEAR;
	double inittime = 0;
	int portr1, portr2, portr3, portl1, portl2, portl3, ports1, ports2;
	public WPI_TalonSRX motorRight;
	public WPI_TalonSRX motorRightFollower1;
	public WPI_TalonSRX motorRightFollower2;
	public WPI_TalonSRX motorLeftFollower2;
	public WPI_TalonSRX motorLeftFollower1;
	public WPI_TalonSRX motorLeft;
	public 	DoubleSolenoid shifter;
	public AHRS ahrs;
	Timer time = new Timer();
	Joystick stick1;
	
	public driveTrain(Timer time, Joystick stick1, int portr1, int portr2, int portr3, int portl1, int portl2, int portl3, int ports1, int ports2) {
		this.gear = State.LOWGEAR;
		this.time = time;
		this.stick1 = stick1;
		this.portr1 = portr1;
		this.portr2 = portr2;
		this.portr3 = portr3;
		this.portl1 = portl1;
		this.portl2 = portl2;
		this.portl3 = portl3;
		this.ports1 = ports1;
		this.ports2 = ports2;
	}
	
	public void init() {
		motorRight = new WPI_TalonSRX(portr1);
		motorRightFollower1 = new WPI_TalonSRX(portr2);
		motorRightFollower2 = new WPI_TalonSRX(portr3);
		motorLeftFollower2 = new WPI_TalonSRX(portl3);
		motorLeftFollower1 = new WPI_TalonSRX(portl2);
		motorLeft = new WPI_TalonSRX(portl1);
		shifter = new DoubleSolenoid(ports1, ports2);
		
		motorRight.set(ControlMode.PercentOutput, 0);
		motorRightFollower1.follow(motorRight);
		motorRightFollower2.follow(motorRight);
		
		motorLeft.set(ControlMode.PercentOutput, 0);
		motorLeft.setInverted(true);
		motorLeftFollower1.follow(motorLeft);
		motorLeftFollower1.setInverted(true);
		motorLeftFollower2.follow(motorLeft);
		motorLeftFollower2.setInverted(true);
		
		motorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		motorNeutralBehavior(NeutralMode.Brake);
	}
	
	public void setmotors(double mr, double ml) {
		motorRight.set(mr);
		motorLeft.set(ml);
	}
	
	public void motorNeutralBehavior(NeutralMode nm) {
		motorLeft.setNeutralMode(nm);
		motorLeftFollower1.setNeutralMode(nm);
		motorLeftFollower2.setNeutralMode(nm);
		motorRight.setNeutralMode(nm);
		motorRightFollower1.setNeutralMode(nm);
		motorRightFollower2.setNeutralMode(nm);
	}
	
	public void upshift() {
		switch(shiftstate) {
		case 0:
			motorNeutralBehavior(NeutralMode.Coast);
			setmotors(0, 0);
			inittime = time.get();
			shiftstate++;
			break;
		case 1:
			shifter.set(DoubleSolenoid.Value.kForward);
			if(time.get() - inittime > .1) {
				shiftstate++;
			}
			break;
		case 2:
			shifter.set(DoubleSolenoid.Value.kOff);
			gear = State.HIGHGEAR;
			shiftstate = 0;
			motorNeutralBehavior(NeutralMode.Brake);
			break;
		}
	}
	
	public void downshift() {
		switch(shiftstate) {
		case 0:
			motorNeutralBehavior(NeutralMode.Coast);
			setmotors(0, 0);
			inittime = time.get();
			shiftstate++;
			break;
		case 1:
			shifter.set(DoubleSolenoid.Value.kReverse);
			if(time.get() - inittime > .1) {
				shiftstate++;
			}
			break;
		case 2:
			shifter.set(DoubleSolenoid.Value.kOff);
			gear = State.LOWGEAR;
			shiftstate = 0;
			motorNeutralBehavior(NeutralMode.Brake);
			break;
		}
	}
	
	
	/*public double lastangle = 0;
	public int anglemultiplier = 0;
	public double angle = 0;
	
	public void updateGyro() {
		float yaw = ahrs.getYaw();
		if (lastangle - yaw > 250) {
			anglemultiplier += 1;
		}
		if (lastangle - yaw < -250) { 
			anglemultiplier -= 1;
		}
		angle = yaw + (360 * anglemultiplier);
		lastangle = yaw;
	}*/
	
	
}
