package frc.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class driverControlwithpad{
    double crntVelocYR;
    double wntdVelocYR;
    double crntVelocYL;
    double wntdVelocYL;
    double straightChanger;
    double turnChangerL;
    double turnChangerR;
    
    public void driveMotors(XboxController gamepad, Talon left, Talon right){
        wntdVelocYR = gamepad.getY(Hand.kRight);
        wntdVelocYL = gamepad.getX(Hand.kLeft);

        crntVelocYL = crntVelocYL+(wntdVelocYL)/10;
        crntVelocYR = crntVelocYR+(wntdVelocYR)/10;
gdhje
        left.set(crntVelocYL);
        right.set(crntVelocYR);
    }   
}
