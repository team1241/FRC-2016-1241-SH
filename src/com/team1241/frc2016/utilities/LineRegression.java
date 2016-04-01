package com.team1241.frc2016.utilities;

public class LineRegression {
	private static double[] rpm;
	private static double[] power;
	private static double rpmSum = 0;
	private static double powerSum = 0;
	private static double productSum = 0;
	private static double squareSum = 0;
	
	private static double slope;
	private static double intercept;
	
	public LineRegression(double[] x, double[] y) {
		this.rpm = x;
		this.power = y;
		rpmSum = 0;
		powerSum = 0;
		productSum = 0;
		squareSum = 0;
		for(int i=0; i<rpm.length; i++) {
			rpmSum = rpmSum+rpm[i];
			powerSum = powerSum+power[i];
			productSum = productSum+ rpm[i]*power[i];
			squareSum = squareSum + Math.pow(rpm[i], 2);
		}
		bestLine();
	}
	
	private static void bestLine() {
		slope = (rpm.length*productSum-rpmSum*powerSum)/rpm.length*squareSum-Math.pow(rpmSum, 2);
		intercept = (powerSum-slope*rpmSum)/rpm.length;
	}
	
	public double getSlope() {
		return slope;
	}
	
	public double getIntercept() {
		return intercept;
	}
}
