//main package
package frc.robot.functions;
//motor driver(Talon, set power and stuff) import
import edu.wpi.first.wpilibj.Talon;
//timer(measures time passed) utility import
import edu.wpi.first.wpilibj.Timer;

public class driveWithTime{
    public driveWithTime(){}
    //driving straight, 
    //"left" or "right" is the left and right motor drives, respectively
    //"power" is the base power set to 
    //"distance" is our wanted distance
    //"timeTurned" is our wanted turn distance
    //boolean variable to tell our main program whether or not to finish
    boolean stateFin;

    //drive straight with time
    public boolean driveStraight(Talon left, Talon right, int power, int distance, Timer timeFunc){
        if(timeFunc.get()<=distance){
            left.set(power);
            right.set(power);
        }
        else{
            left.set(0);
            right.set(0);
        }

        return(stateFin);

    }
    //turn with time
    public void driveTurn(Talon left, Talon right, double power, double turn,
    Double timeTurned, Timer timeFunc){
        if(timeFunc.get()<turn){
            left.set(power);
            right.set(power);
        }
        else{
            left.set(0);
            right.set(0);
        }
    }
}