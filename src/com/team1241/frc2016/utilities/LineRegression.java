package com.team1241.frc2016.utilities;

public class LineRegression {
	private double[] rpm;
	private double[] power;
	private static double rpmSum = 0;
	private static double powerSum = 0;
	private static double productSum = 0;
	private static double squareSum = 0;
	
	private static double slope;
	private static double intercept;
	
	public LineRegression() {
	}
	
	public LineRegression(double[] x, double[] y) {
		this.setValues(x, y);
	}
	
	public void setValues(double[] x, int[] y) {
		double[] temp = new double[y.length];
		for(int i=0; i<y.length; i++) {
			temp[i] = (double)y[i];
		}
		this.setValues(x, temp);
	}
	
	public void setValues(int[] x, double[] y) {
		double[] temp = new double[x.length];
		for(int i=0; i<x.length; i++) {
			temp[i] = (double)x[i];
		}
		this.setValues(temp, y);
	}
	
	public void setValues(int[] x, int[] y) {
		double[] temp = new double[x.length];
		for(int i=0; i<x.length; i++) {
			temp[i] = (double)x[i];
		}
		this.setValues(temp, y);
	}
	
	public void setValues(double[] x, double[] y) {
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
	
	private void bestLine() {
		slope = (rpm.length*productSum-rpmSum*powerSum)/rpm.length*squareSum-Math.pow(rpmSum, 2);
		intercept = (powerSum-slope*rpmSum)/rpm.length;
	}
	
	public double getSlope() {
		return slope;
	}
	
	public double getIntercept() {
		return intercept;
	}
	
	public static void main(String[]args) {
		LineRegression lr = new LineRegression();
		double[] rpm = new double[]{4000, 4100, 4200, 4500, 5000};
		double[] power = new double[]{0.6875, 0.705, 0.7175, 0.765, 0.8375};
		lr.setValues(rpm, power);
		System.out.println("Slope: "+lr.getSlope()+" intercept: "+lr.getIntercept());
	}
}
