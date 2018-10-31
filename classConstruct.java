package frc.robot.functions;


import frc.robot.functions.driveWithEncoders;
import frc.robot.functions.driveWithTime;
import frc.robot.functions.moveServos;
import frc.robot.functions.driveWithAHRS;

public class classConstruct{
    public int yes;
    public String typehere;
    //this is so cool aaaaaaaa please be proud of me :(
    public classConstruct(){};

    public static Object[] constructingClasses(){
        //setting objects to be apart of the returned array
        //basically makes a bunch of callable classes to 
        //drive motors, etc in one spot and it makes things all nice
        //and organized and pretty

        //the object fortnite is not really used anyways in the main
        //code cause were just going 
        //classConstruct.constructingClasses()[0]; to call 
        //driving with time, for example
        //set each of these to a new thing, like you would a class
        //but just through a bunch of bullshit lol
        //lets say "fortnite" is the name 
        //[0]-driveWithAHRS
        //[1]-driveWithTime
        //[2]-driveWithEncoders
        //[3]-moveServos
        //we will keep adding classes to be made here
        Object[] fortnite = 
        {
            new driveWithAHRS(),
            new driveWithTime(), 
            new driveWithEncoders(),
            new moveServos()
        };
        //this is returning the var as what we want the class
        //to like, be in a sense
        return(fortnite);
    }

}