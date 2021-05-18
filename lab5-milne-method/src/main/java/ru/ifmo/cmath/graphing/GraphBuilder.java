package ru.ifmo.cmath.graphing;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import ru.ifmo.cmath.algebra.Function;
import ru.ifmo.cmath.utils.Point;

import java.util.List;

public class GraphBuilder extends Application {

    @FXML private LineChart<Number, Number> lineChart;
    private static Function originalFunc = null;
    private static Function lagrangeFunc = null;
    private static List<Point> lagrangePoints = null;
    private double xLowerLimit = -10d;
    private double xUpperLimit = 10d;
    private double yLowerLimit = -10d;
    private double yUpperLimit = 10d;
    private double funcStep = 0.001d;

    public static void setData(Function originalFunc, Function lagrangeFunc) {
        GraphBuilder.originalFunc = originalFunc;
        GraphBuilder.lagrangeFunc = lagrangeFunc;
    }

    public static void setData(Function originalFunc, List<Point> lagrangePoints) {
        GraphBuilder.originalFunc = originalFunc;
        GraphBuilder.lagrangePoints = lagrangePoints;
    }

    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        lineChart = new LineChart<Number, Number>(
//                new NumberAxis(((int)xLowerLimit), ((int)xUpperLimit), 2),
//                new NumberAxis(((int)yLowerLimit), ((int)yUpperLimit), 2));

        LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());
        Scene scene = new Scene(lineChart, 900, 600);

//        XYChart.Series series1 = createDrawableFunc(originalFunc);
//        series1.setName("Original function f(x)");

        XYChart.Series series2 = null;
        if (lagrangeFunc != null)
            series2 = createDrawableFunc(lagrangeFunc);
        else
            series2 = drawPoints(lagrangePoints);
        series2.setName("Final function L(x)");

        lineChart.getData().addAll(series2);

        //remove ugly visual points from functions
        removeChartLineSymbol(series2);

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

    private XYChart.Series drawPoints(List<Point> axisData) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for (Point point : axisData) {
            series.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
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
