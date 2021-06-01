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
    private static List<Point> nodes = null;
    private static double lower = -10;
    private static double upper = 10;

    public static void setData(Function originalFunc, Function lagrangeFunc, List<Point> nodes, double lower, double upper) {
        GraphBuilder.originalFunc = originalFunc;
        GraphBuilder.lagrangeFunc = lagrangeFunc;
        GraphBuilder.nodes = nodes;
        GraphBuilder.lower = lower;
        GraphBuilder.upper = upper;
    }

    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());

        XYChart.Series series1 = createDrawableFunc(originalFunc);
        series1.setName("Original function f(x)");

        XYChart.Series series2 = createDrawableFunc(lagrangeFunc);
        series2.setName("Interpolational function");

        XYChart.Series series3 = new XYChart.Series<>();
        for (Point node : nodes) {
            series3.getData().add(new XYChart.Data<>(node.getX(), node.getY()));
        }
        series3.setName("Interpolational points");


        lineChart.getData().addAll(series1, series2, series3);
        //lineChart.getStylesheets().add("ru/ifmo/cmath/graphing/chart-styles.css");

        //remove ugly visual points from functions
        removeChartLineSymbol(series1, series2);

        Scene scene = new Scene(lineChart, 900, 600);
        stage.setTitle("Interpolation with Lagrangian Polynomial using Chebyshev Nodes");
        stage.setScene(scene);
        stage.show();
    }

    private XYChart.Series<Double, Double> createDrawableFunc(Function function) {
        XYChart.Series<Double, Double> series = new XYChart.Series<>();

        int count = 1024;
        double step = (upper - lower) / (count - 1);
        for (int i = 0; i < count; i++) {
            double val = lower + i * step;
            series.getData().add(new XYChart.Data<>(val, function.apply(val)));
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
