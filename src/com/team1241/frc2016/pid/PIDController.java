package com.team1241.frc2016.pid;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Team 1241
 * Simple PID Controller that assumes regular loop intervals 
 */
public class PIDController {
    double pGain;
    double iGain;
    double dGain;
    
    double pOut;
    double iOut;
    double dOut;
    
    double error;
    double errorSum = 0;
    double lastError = 0;
    double dProcessVar;
    double maxVal = 0;
    double output = 0;
    double prevOutput = 0;
    
    double previousValue = 0;
    double previousAverage = 0;
    double currentAverage;
    double average;
    
    boolean atTarget = false;
    
    public PIDController(double p, double i, double d) {   
        errorSum = 0;       //initialize errorSum to 0
        lastError = 0;      //initialize lastError to 0 
        pGain = p;
        iGain = i;
        dGain = d;
    }
            
    public void resetIntegral() {
        errorSum = 0.0;
    }
    
    public void resetDerivative() {
       lastError = 0.0;
    }
    
    public void resetPID(){
    	resetIntegral();
    	resetDerivative();
    	atTarget = false;
    	maxVal = 0;
    	prevOutput = 0;
    }
    
    public void changePIDGains(double kP, double kI, double kD) {
        pGain = kP;
        iGain = kI;
        dGain = kD;
    }
    
    public double calcPID(double setPoint, double currentValue, double epsilon) {
        error = setPoint - currentValue;
        
        if(Math.abs(error) <= epsilon){
        	atTarget = true;
        }
        else{
        	atTarget = false;
        }
        
        //P 
        pOut = pGain * error;
        
        //I
        errorSum += error;
        iOut = iGain * errorSum;
        
        //D
        dProcessVar = (error - lastError);
        dOut = dGain * dProcessVar;
        
        lastError = error;
        
        //PID Output
        output = pOut + iOut + dOut;
        
      //Scale output to be between 1 and -1
        if(output!=0.0)
        	output = output/Math.abs(output)*(1.0 - Math.pow(0.1,(Math.abs(output))));
        
        return output;
    }
    
    public double calcPIDVelocity(double setPoint, double currentValue, double epsilon) {
        error = setPoint - currentValue;
        
        
        if(Math.abs(setPoint-average) <= epsilon){
        	atTarget = true;
        }
        else{
        	atTarget = false;
        }
        
        //P 
        pOut = pGain * error;
        
        //I
        if(currentValue >= setPoint*0.60){
	        errorSum += error;
	        iOut = iGain * errorSum;
	        
	        currentAverage = (previousValue+currentValue)/2;
	        average = (currentAverage+previousAverage)/2;
	        SmartDashboard.putNumber("average", average);
        }
        
        //D
        dProcessVar = (error - lastError);
        dOut = dGain * dProcessVar;
        
        lastError = error;
        previousValue = currentValue;
        previousAverage = currentAverage;
        
        //PID Output
        output = pOut + iOut + dOut;
        
      //Scale output to be between 1 and -1
        if(output!=0.0)
        	output = output/Math.abs(output)*(1.0 - Math.pow(0.1,(Math.abs(output))));
        
        return output;
    }
    
    public boolean isDone(){
    	return atTarget;
    }
    
    public double getPGain() {
    	return pGain;
    }
    
    public double getIGain() {
    	return iGain;
    }
    
    public double getDGain() {
    	return dGain;
    }
}	