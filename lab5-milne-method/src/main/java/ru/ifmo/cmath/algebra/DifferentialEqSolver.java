package ru.ifmo.cmath.algebra;

import ru.ifmo.cmath.utils.Point;

import java.util.ArrayList;
import java.util.List;


public class DifferentialEqSolver {

    public static final double BASE_ACCURACY = 1e-7;
    public static final double LIMIT = 10_000_000;
    private double ACCURACY = BASE_ACCURACY;
    private double STEP = 0.01;

    public List<Point> solveByMilne(Function function, Point initialPoint, double end) {
        List<Point> points = new ArrayList<>();
        points.add(initialPoint);

        /* Find a count of segments using specified inequality. */
        //int count = (int) ceil((end - initialPoint.getX()) / pow(ACCURACY, 0.25));
        /* Calculate a segments' length. */
        //double step = (end - initialPoint.getX()) / count;

        int i = 1;
        double initialXi = initialPoint.getX() + STEP;
        for (double xi = initialXi; xi <= end; xi += STEP) {
            if (i <= 3) {
                points.add(new Point(xi, getYByRungeKutta(function, points.get(i-1))));
            } else {
                points.add(pointByMilne(function, points, xi, i));
            }
            i++;
        }
        return points;
    }

    /*The calculated point would be y_i+1 because we are using the last added point in the list
    * to calculate this one*/
    private double getYByRungeKutta(Function func, Point point) {
        double[] k = new double[4];

        k[0] = STEP * func.apply(point.getX(), point.getY());
        k[1] = STEP * func.apply(point.getX() + STEP/2, point.getY() + k[0]/2);
        k[2] = STEP * func.apply(point.getX() + STEP/2, point.getY() + k[1]/2);
        k[3] = STEP * func.apply(point.getX() + STEP, point.getY() + k[2]);

        double delta = (k[0] + 2 * k[1] + 2 * k[2] + k[3]) / 6;

        return point.getY() + delta;
    }

    private Point pointByMilne(Function function, List<Point> points, double xi, int i) {
        double forecast = 0, forecastEvaluated = 0, correction = 0;

        double y2 = points.get(i - 2).getY();
        double y4 = points.get(i - 4).getY();
        double f1 = function.apply(points.get(i-1).getX(), points.get(i-1).getY());
        double f2 = function.apply(points.get(i-2).getX(), points.get(i-2).getY());
        double f3 = function.apply(points.get(i-3).getX(), points.get(i-3).getY());

        forecast = y4 + 4*STEP/3 * (2*f3 - f2 + 2*f1);
        forecastEvaluated = function.apply(xi, forecast);
        correction = y2 + STEP/3 * (f2 - 4*f1 + 2*forecastEvaluated);
        double yc = 0;
        do {
            yc = correction;
            forecastEvaluated = function.apply(xi, yc);
            correction = y2 + STEP/3 * (f2 - 4*f1 + 2*forecastEvaluated);
        } while (Math.abs(correction - yc) >= ACCURACY);

        return new Point(xi, correction);
    }

    public double getAccuracy() {
        return ACCURACY;
    }
    public void setAccuracy(double ACCURACY) {
        this.ACCURACY = ACCURACY;
    }
    public double getStep() {
        return STEP;
    }
    public void setStep(double STEP) {
        this.STEP = STEP;
    }
}
