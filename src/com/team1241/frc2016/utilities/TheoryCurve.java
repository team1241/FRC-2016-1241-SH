/*
 * Author: Mahrus Kazi
 * 
 * Reference Material:
 * https://www.youtube.com/watch?v=ct_uGOSPtok
 * https://www.desmos.com/calculator/cahqdxeshd
 */

package com.team1241.frc2016.utilities;

import java.util.Vector;

public class TheoryCurve {
  public Vector vector = new Vector();
  public Vector xPoints = new Vector();
  public Vector yPoints = new Vector();
  public double[] hypotenuse = new double[20];
  public double[] xDelta = new double[20];
  public double[] yDelta = new double[20];
  public double[] angle = new double[20];
  public double[] xValues = new double[4];
  public double[] yValues = new double[4];
  public double distance;
  
  public TheoryCurve (String startPoint, String controlPoint1, String controlPoint2, String endPoint)
  {
    vector.addElement(startPoint);
    vector.addElement(controlPoint1);
    vector.addElement(controlPoint2);
    vector.addElement(endPoint);
    putPoints();
    findPoints();
    calcPoints();
  }
  
  public void changePoints (String startPoint, String controlPoint1, String controlPoint2, String endPoint)
  {
	vector.removeAllElements();
    vector.addElement(startPoint);
    vector.addElement(controlPoint1);
    vector.addElement(controlPoint2);
    vector.addElement(endPoint);
    putPoints();
    findPoints();
    calcPoints();
  }
  
  public Vector findPoints()
  {
    Vector points = new Vector();
    xPoints.removeAllElements();
    yPoints.removeAllElements();
    
    for(double x = 0; x <= 1.05; x+=0.05){
    	xPoints.addElement(useFunctionX(xValues[0], xValues[1], xValues[2], xValues[3], x));
    	yPoints.addElement(useFunctionY(yValues[0], yValues[1], yValues[2], yValues[3], x));
    	points.addElement(useFunctionX(xValues[0], xValues[1], xValues[2], xValues[3], x) + ", " + useFunctionY(yValues[0], yValues[1], yValues[2], yValues[3], x));
    }
    
    return points;
  }
  
  public double useFunctionX(double zeroX, double oneX, double twoX, double threeX, double time)
  {
    double num = Math.pow(1 - time, 3)*zeroX;
    double num2 = 3*Math.pow(1 - time, 2)*time*oneX;
    double num3 = 3*(1 - time)*Math.pow(time, 2)*twoX;
    double num4 = Math.pow(time, 3)*threeX;
    
    return num + num2 + num3 + num4;
  }
  
  public double useFunctionY(double zeroY, double oneY, double twoY, double threeY, double time)
  {
    double num = Math.pow(1 - time, 3)*zeroY;
    double num2 = 3*Math.pow(1 - time, 2)*time*oneY;
    double num3 = 3*(1 - time)*Math.pow(time, 2)*twoY;
    double num4 = Math.pow(time, 3)*threeY;
    
    return num + num2 + num3 + num4;
  }
  
  public void putPoints()
  {
    String point;
    String xPoint;
    String yPoint;
    double x;
    double y;
    int commaPos;
    for(int i = 0; i < vector.size(); i++){
        point = vector.elementAt(i).toString();
        commaPos = point.indexOf(",");
        xPoint = point.substring(0, commaPos).trim();
        x = Double.parseDouble(xPoint);
        yPoint = point.substring(commaPos+1).trim();
        y = Double.parseDouble(yPoint);        
        xValues[i] = x;
        yValues[i] = y;
    }  
  }
  
  public void calcPoints(){
	  for(int x = 0; x < xDelta.length; x++){
		  xDelta[x] = (Double.parseDouble(xPoints.elementAt(x + 1).toString()) - Double.parseDouble(xPoints.elementAt(x).toString()));
		  yDelta[x] = (Double.parseDouble(yPoints.elementAt(x + 1).toString()) - Double.parseDouble(yPoints.elementAt(x).toString()));
		  
		  if(xDelta[x] == 0){
			  if(yDelta[x] > 0)
				  angle[x] = 0;
			  else if(yDelta[x] < 0)
				  angle[x] = 180;
		  }
		  else if(yDelta[x] == 0){
			  if(xDelta[x] > 0)
				  angle[x] = 90;
			  else if(xDelta[x] < 0)
				  angle[x] = 270;
		  }
		  else
			  angle[x] = Math.toDegrees(Math.atan2(xDelta[x], yDelta[x]));
		  
		  distance += Math.sqrt(Math.pow(xDelta[x], 2) + Math.pow(yDelta[x], 2));
		  hypotenuse[x] = distance;
	  }
  }
  
  public double findDistance(){
	  return distance;
  }
  
  public double findAngle(int index){
	  return angle[index];
  }
  
  public double findHypotenuse(int index){
	  return hypotenuse[index];
  }
  
  public double findIndex(){
	  return xDelta.length;
  }
  
  public double findXDelta(int x){
	  return xDelta[x];
  }
  
  public double findYDelta(int y){
	  return yDelta[y];
  }
  
}
