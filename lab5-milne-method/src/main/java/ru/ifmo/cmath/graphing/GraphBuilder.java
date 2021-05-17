package ru.ifmo.cmath.graphing;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import ru.ifmo.cmath.algebra.Function;

public class GraphBuilder extends Application {

    @FXML private LineChart<Number, Number> lineChart;
    private static Function originalFunc = null;
    private static Function lagrangeFunc = null;
    private double xLowerLimit = -10d;
    private double xUpperLimit = 10d;;
    private double yLowerLimit = -10d;
    private double yUpperLimit = 10d;
    private double funcStep = 0.001d;

    public static void setData(Function originalFunc, Function lagrangeFunc) {
        GraphBuilder.originalFunc = originalFunc;
        GraphBuilder.lagrangeFunc = lagrangeFunc;
    }

    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        lineChart = new LineChart<Number, Number>(
//                new NumberAxis(((int)xLowerLimit), ((int)xUpperLimit), 2),
//                new NumberAxis(((int)yLowerLimit), ((int)yUpperLimit), 2));

        LineChart<Number, Number> chart = new LineChart<>(new NumberAxis(), new NumberAxis());
        Scene scene = new Scene(lineChart, 900, 600);

        XYChart.Series series1 = createDrawableFunc(originalFunc);
        series1.setName("Original function f(x)");
        XYChart.Series series2 = createDrawableFunc(lagrangeFunc);
        series2.setName("Final function L(x)");

        lineChart.getData().addAll(series1, series2);

        //remove ugly visual points from functions
        removeChartLineSymbol(series1, series2);

        stage.setTitle("Ordinary Differential Equation Solver (Milne's Method)");
        stage.setScene(scene);
        stage.show();
    }

    private XYChart.Series createDrawableFunc(Function function) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (double xPoint = xLowerLimit; xPoint <= xUpperLimit; xPoint += funcStep) {
            series.getData().add(new XYChart.Data<>(xPoint, function.apply(xPoint)));
        }
        return series;
    }

    private XYChart.Series createDrawablePointsFromFunc(Function function, Double[] points) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (Double xPoint : points) {
            series.getData().add(new XYChart.Data<>(xPoint, function.apply(xPoint)));
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
