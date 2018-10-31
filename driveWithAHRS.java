package frc.robot.functions;

import edu.wpi.first.wpilibj.Talon;

import com.kauailabs.navx.frc.AHRS;

public class driveWithAHRS{
    public driveWithAHRS(){}
    //turn with navx
    boolean stateFin;
    public boolean driveTurn(Talon left, Talon right, double power, double turn, AHRS ahrs){

        return(stateFin);
    }
    public boolean driveStraight(Talon left, Talon right, double power, double distance, AHRS ahrs){

        return(stateFin);
    }

}