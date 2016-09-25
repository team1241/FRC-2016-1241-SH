package com.team1241.frc2016.utilities;

/*
 * Class that generates a Bezier Curve based of off 4 coordinates given by the client
 * 
 * Reference Material:
 * https://www.desmos.com/calculator/cahqdxeshd
 * 
 * @author Mahrus Kazi
 * @version 2.0, 25 Sep 2016
 */

import java.util.List;
import java.util.ArrayList;

public class BezierCurve {
	private static int SIZE = 1000;
	
	public Point[] vector = new Point[4];
	public List<Double> xPoints = new ArrayList<Double>();
	public List<Double> yPoints = new ArrayList<Double>();
	public List<Double> hypotenuse = new ArrayList<Double>();
	public List<Double> xDelta = new ArrayList<Double>();
	public List<Double> yDelta = new ArrayList<Double>();
	public List<Double> angle = new ArrayList<Double>();
	public double[] xValues = new double[4];
	public double[] yValues = new double[4];
	public double distance;

	/**
	 * Requires 4 points to generate Bezier Curves, inputed as Points
	 * 
	 * @param startPoint
	 * @param controlPoint1
	 * @param controlPoint2
	 * @param endPoint
	 */
	public BezierCurve(Point startPoint, Point controlPoint1, Point controlPoint2, Point endPoint) {
		vector[0] = startPoint;
		vector[1] = controlPoint1;
		vector[2] = controlPoint2;
		vector[3] = endPoint;
		putPoints();
		findPoints();
		calcPoints();
	}
	
	public BezierCurve(Point startPoint, Point controlPoint1, Point controlPoint2, Point endPoint, int size) {
		vector[0] = startPoint;
		vector[1] = controlPoint1;
		vector[2] = controlPoint2;
		vector[3] = endPoint;
		SIZE = size;
		putPoints();
		findPoints();
		calcPoints();
	}

	/**
	 * Update given coordinates
	 * 
	 * @param startPoint
	 * @param controlPoint1
	 * @param controlPoint2
	 * @param endPoint
	 */
	public void changePoints(Point startPoint, Point controlPoint1, Point controlPoint2, Point endPoint) {
		vector[0] = startPoint;
		vector[1] = controlPoint1;
		vector[2] = controlPoint2;
		vector[3] = endPoint;
		putPoints();
		findPoints();
		calcPoints();
	}

	/**
	 * Generates the points on the Bezier Curve
	 * 
	 * @return Returns a ArrayList consisting of Points
	 */
	public ArrayList<String> findPoints() {
		ArrayList<String> points = new ArrayList<String>();
		double xVal;
		double yVal;
		xPoints.clear();
		yPoints.clear();

		for (double x = 0; x <= 1; x += 1/SIZE) {
			xVal = useFunctionX(xValues[0], xValues[1], xValues[2], xValues[3], x);
			yVal = useFunctionY(yValues[0], yValues[1], yValues[2], yValues[3], x);
			xPoints.add(xVal);
			yPoints.add(yVal);
			points.add(xVal + ", " + yVal);
		}

		return points;
	}

	/**
	 * Generates x points for Bezier Curve
	 * 
	 * @param x0
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param counter
	 * @return Returns the x values for the coordinate
	 */
	public double useFunctionX(double x0, double x1, double x2, double x3, double counter) {
		double cx = 3 * (x1 - x0);
		double bx = 3 * (x2 - x1) - cx;
		double ax = x3 - x0 - cx - bx;
		double xVal = ax * Math.pow(counter, 3) + bx * Math.pow(counter, 2) + cx * counter + x0;

		return xVal;
	}

	/**
	 * Generates y points for Bezier Curve
	 * 
	 * @param y0
	 * @param y1
	 * @param y2
	 * @param y3
	 * @param counter
	 * @return Returns the y values for the coordinate 
	 */
	public double useFunctionY(double y0, double y1, double y2, double y3, double counter) {
		double cy = 3 * (y1 - y0);
		double by = 3 * (y2 - y1) - cy;
		double ay = y3 - y0 - cy - by;
		double yVal = ay * Math.pow(counter, 3) + by * Math.pow(counter, 2) + cy * counter + y0;

		return yVal;
	}

	public void putPoints() {
		Point point;

		for (int i = 0; i < vector.length; i++) {
			point = vector[i];
			xValues[i] = point.getX();
			yValues[i] = point.getY();
		}
	}

	public void calcPoints() {
		for (int x = 0; x < xPoints.size() - 1; x++) {
			xDelta.add((xPoints.get(x + 1) - xPoints.get(x)));
			yDelta.add((yPoints.get(x + 1) - yPoints.get(x)));

			if (xDelta.get(x) == 0) {
				if (yDelta.get(x) > 0)
					angle.add(x, 0.0);
				else if (yDelta.get(x) < 0)
					angle.add(x, 180.0);
			} else if (yDelta.get(x) == 0) {
				if (xDelta.get(x) > 0)
					angle.add(x, 90.0);
				else if (xDelta.get(x) < 0)
					angle.add(x, -90.0);
			} else
				angle.add(Math.toDegrees(Math.atan2(xDelta.get(x), yDelta.get(x))));

			distance += Math.sqrt(Math.pow(xDelta.get(x), 2) + Math.pow(yDelta.get(x), 2));
			hypotenuse.add(x, distance);
		}
	}

	public double findDistance() {
		return distance;
	}

	public double findAngle(int index) {
		return angle.get(index);
	}

	public double findHypotenuse(int index) {
		return hypotenuse.get(index);
	}

	public double findIndex() {
		return xDelta.size();
	}

	public double findXDelta(int x) {
		return xDelta.get(x);
	}

	public double findYDelta(int y) {
		return yDelta.get(y);
	}

	public List<Double> getXPoints() {
		return xPoints;
	}

	public List<Double> getYPoints() {
		return yPoints;
	}

	public int size() {
		return SIZE-1;
	}

}
