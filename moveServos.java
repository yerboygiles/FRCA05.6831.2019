package frc.robot.functions;

import edu.wpi.first.wpilibj.Servo;

public class moveServos{
    public void moveWithAngle(Servo servo,double angleVal){

        servo.setAngle(angleVal);

    }
    public boolean moveWithValue(Servo servo,double servoVal){

        servo.set(servoVal);
        if(servo.get()==servoVal){
            return(true);

        }
        else{
            return(false);
        }

    }
    
}