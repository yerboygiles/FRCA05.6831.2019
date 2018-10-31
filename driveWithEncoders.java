package frc.robot.functions;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;

public class driveWithEncoders extends classConstruct{

    //variable to store "((encLeft.get()+encRight.get())/2)" in
    double encAvg;
    //variable to store the differentiating encoder counts
    double encDiff;
    //variable to store a buncha cool math but its not really that cool but whatever
    double encDiffPow;
    //boolean variable to tell our main program whether or not to finish
    boolean stateFin;
    public driveWithEncoders(){}
    public boolean driveTurn(Talon left, 
    Talon right, double power, double turn,
    Encoder encLeft, Encoder encRight){
        encAvg = ((encLeft.get()+encRight.get())/2);
        encDiff = (encLeft.get()-encRight.get());
        if (encLeft.get()>encRight.get()){
        }

        else if (encRight.get()<encRight.get()){
        }
        
        if(encAvg<=turn){
            left.set(power);
            right.set(power);
        }

        else{
            left.set(0);
            right.set(0);
        }
        return(stateFin);
    }

    //drive straight with encoders
    public boolean driveStraight(Talon left, 
    Talon right, double power, double distance, 
    Encoder encLeft, Encoder encRight){
        encAvg = ((encLeft.get()+encRight.get())/2);
        encDiff = (encLeft.get()-encRight.get());
        if (encLeft.get()>encRight.get()){

        }
        else if (encRight.get()<encRight.get()){

        }
        else{
            encDiffPow=0;
        }

        if(encAvg<=distance){
            left.set(power);
            right.set(power);
        }
        else{
            left.set(0);
            right.set(0);
            stateFin=false;
        }
        return(stateFin);
    }
}