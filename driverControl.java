package frc.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;

public class driverControl{
    double crntVelocY;
    double wntdVelocY;
    double crntVelocZ;
    double wntdVelocZ;
    double straightChanger;
    double turnChangerL;
    double turnChangerR;
    public void driveMotors(Joystick joystick, Talon left, Talon right){
        wntdVelocY = -joystick.getRawAxis(2);
        wntdVelocZ= joystick.getRawAxis(3);
        if(wntdVelocY>.1){
            if(wntdVelocY<=crntVelocY){
                crntVelocY-=(wntdVelocY/10);
            }
            else if(wntdVelocY>crntVelocY){
                crntVelocY+=(wntdVelocY/10);
            }
        }
        else if(wntdVelocY<-.1){
            if(wntdVelocY==crntVelocY){
                crntVelocY=crntVelocY+(wntdVelocY/10);
            }
            else if(wntdVelocY<crntVelocY){
                crntVelocY-=(wntdVelocY/10);
            }
            else if(wntdVelocY>crntVelocY){
                crntVelocY+=(wntdVelocY/10);
            }    
        }
        else{
            crntVelocY=0;
        }
        straightChanger=crntVelocY;
        if(wntdVelocZ>.1){
            if(wntdVelocZ==crntVelocZ){
                crntVelocZ=crntVelocZ+(wntdVelocZ/10);
            }
            else if(wntdVelocZ<crntVelocZ){
                crntVelocZ-=(wntdVelocZ/10);
            }
            else if(wntdVelocZ>crntVelocZ){
                crntVelocZ+=(wntdVelocZ/10);
            }
            turnChangerR=-crntVelocZ;
            turnChangerL=crntVelocZ;
        }
        else if(wntdVelocZ<-.1){
            if(wntdVelocZ==crntVelocZ){
                crntVelocZ=crntVelocZ+(wntdVelocZ/10);
            }
            else if(wntdVelocZ<crntVelocZ){
                crntVelocZ-=(wntdVelocZ/10);
            }
            else if(wntdVelocZ>crntVelocZ){
                crntVelocZ+=(wntdVelocZ/10);
            }
            turnChangerR=crntVelocZ;
            turnChangerL=-crntVelocZ;
        }
        turnChangerR=0;
        turnChangerL=0;
        left.set(crntVelocY-(turnChangerL/2)+turnChangerL-(straightChanger/2));
        right.set(crntVelocY-(turnChangerR/2)+turnChangerR-(straightChanger/2));
    }
}