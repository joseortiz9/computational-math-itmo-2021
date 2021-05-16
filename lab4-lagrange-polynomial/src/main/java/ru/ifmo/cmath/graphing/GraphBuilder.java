package ru.ifmo.cmath.graphing;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import ru.ifmo.cmath.algebra.IFunction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphBuilder extends Application {

    @FXML private LineChart<Number, Number> lineChart;
    private static IFunction originalFunc = null;
    private static IFunction lagrangeFunc = null;
    private static Double[] xPoints = null;
    private static Double[] yPoints = null;
    private double xLowerLimit = -10d;
    private double xUpperLimit = 10d;;
    private double yLowerLimit = -10d;
    private double yUpperLimit = 10d;
    private double funcStep = 0.001d;

    public static void setData(IFunction originalFunc, IFunction lagrangeFunc, Double[] xPoints, Double[] yPoints) {
        GraphBuilder.originalFunc = originalFunc;
        GraphBuilder.lagrangeFunc = lagrangeFunc;
        GraphBuilder.xPoints = xPoints;
        GraphBuilder.yPoints = yPoints;
    }

    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        List<Double> xPointsList = Arrays.asList(xPoints);
        List<Double> yPointsList = Arrays.asList(yPoints);
        xLowerLimit = Collections.min(xPointsList); xUpperLimit = Collections.max(xPointsList);
        yLowerLimit = Collections.min(yPointsList); yUpperLimit = Collections.max(yPointsList);
        funcStep = 0.2;
        //funcStep = (lowerLimit - upperLimit) / 4096;
        xLowerLimit -= 10; xUpperLimit += 10;
        yLowerLimit -= 10; yUpperLimit += 10;

        lineChart = new LineChart<Number, Number>(
                new NumberAxis(((int)xLowerLimit), ((int)xUpperLimit), 2),
                new NumberAxis(((int)yLowerLimit), ((int)yUpperLimit), 2));
        Scene scene = new Scene(lineChart, 900, 600);

        XYChart.Series series1 = createDrawableFunc(originalFunc);
        series1.setName("Original function f(x)");
        XYChart.Series series2 = createDrawableFunc(lagrangeFunc);
        series2.setName("Lagrange polynomial L(x)");
        XYChart.Series series3 = createDrawablePointsFromFunc(originalFunc, xPoints);
        series3.setName("Given points");

        lineChart.getData().addAll(series1, series2, series3);

        //remove ugly visual points from functions
        removeChartLineSymbol(series1, series2);

        stage.setTitle("Interpolation with Lagrange Polynomial");
        stage.setScene(scene);
        stage.show();
    }

    private XYChart.Series createDrawableFunc(IFunction function) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (double xPoint = xLowerLimit; xPoint <= xUpperLimit; xPoint += funcStep) {
            series.getData().add(new XYChart.Data<>(xPoint, function.solveForX(xPoint)));
        }
        return series;
    }

    private XYChart.Series createDrawablePointsFromFunc(IFunction function, Double[] points) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (Double xPoint : points) {
            series.getData().add(new XYChart.Data<>(xPoint, function.solveForX(xPoint)));
        }
        return series;
    }

    private void removeChartLineSymbol(XYChart.Series<Double, Double>... series) {
        for (XYChart.Series<Double, Double> s : series) {
            for (XYChart.Data<Double, Double> data : s.getData()) {
                data.getNode().setVisible(false);
            }
        }
    }
}
