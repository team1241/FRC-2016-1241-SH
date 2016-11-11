package com.team1241.frc2016.utilities;

/**
 * Class used to calculate the line of regression. Used to find feed forward for
 * shooter velocity controller.
 * 
 * @author Bryan
 */
public class LineRegression {

	private int N;
	private double[] x;
	private double[] y;
	private static double xSum = 0;
	private static double ySum = 0;
	private static double xSquaredSum = 0;
	private static double xAverage = 0;
	private static double yAverage = 0;
	private static double slope;
	private static double intercept;

	/**
	 * Instantiates a new line regression.
	 */
	public LineRegression() {
	}

	/**
	 * Instantiates a new line regression.
	 *
	 * @param x
	 *            Array of x values
	 * @param y
	 *            Array of y values
	 */
	public LineRegression(double[] x, double[] y) {
		this.setValues(x, y);
	}

	/**
	 * Sets the values.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setValues(double[] x, int[] y) {
		double[] temp = new double[y.length];
		for (int i = 0; i < y.length; i++) {
			temp[i] = (double) y[i];
		}
		this.setValues(x, temp);
	}

	/**
	 * Sets the values.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setValues(int[] x, double[] y) {
		double[] temp = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			temp[i] = (double) x[i];
		}
		this.setValues(temp, y);
	}

	/**
	 * Sets the values.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setValues(int[] x, int[] y) {
		double[] temp = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			temp[i] = (double) x[i];
		}
		this.setValues(temp, y);
	}

	/**
	 * Sets the values.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setValues(double[] x, double[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("array lengths are not equal");
		}
		N = x.length;
		this.x = x;
		this.y = y;

		xSum = 0;
		ySum = 0;
		xSquaredSum = 0;
		for (int i = 0; i < N; i++) {
			xSum = xSum + x[i];
			ySum = ySum + y[i];
			xSquaredSum = xSquaredSum + Math.pow(x[i], 2);
		}
		xAverage = xSum / N;
		yAverage = ySum / N;

		bestLine();
	}

	/**
	 * Best line.
	 */
	private void bestLine() {
		// second pass: compute summary statistics
		double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
		for (int i = 0; i < N; i++) {
			xxbar += (x[i] - xAverage) * (x[i] - xAverage);
			yybar += (y[i] - yAverage) * (y[i] - yAverage);
			xybar += (x[i] - xAverage) * (y[i] - yAverage);
		}
		slope = xybar / xxbar;
		intercept = yAverage - slope * xAverage;
	}

	/**
	 * Gets the slope.
	 *
	 * @return the slope
	 */
	public double getSlope() {
		return slope;
	}

	/**
	 * Gets the intercept.
	 *
	 * @return the intercept
	 */
	public double getIntercept() {
		return intercept;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		LineRegression lr = new LineRegression();
		int[] rpm = new int[] { 4000, 4100, 4200, 4500, 5000 };
		double[] power = new double[] { 0.6875, 0.705, 0.7175, 0.765, 0.8375 };
		lr.setValues(rpm, power);
		System.out.println("Slope: " + lr.getSlope() + " intercept: " + lr.getIntercept());
	}
}
