package ru.ifmo.cmath.graphing;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import ru.ifmo.cmath.algebra.IFunction;
import ru.ifmo.cmath.algebra.ISystem;
import ru.ifmo.cmath.algebra.Point;

public class GraphBuilder extends Application {
    private static Object type = null;
    private static Point point = null;

    public static void setData(Object type) {
        GraphBuilder.type = type;
    }
    public static void setSolution(Point point) {
        GraphBuilder.point = point;
    }

    @FXML public LineChart lineChart;

    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        lineChart = new LineChart(
                new NumberAxis(-10, 10, 2),
                new NumberAxis(-10, 10, 2)
        );
        Scene scene = new Scene(lineChart, 900, 600);

        if (GraphBuilder.type instanceof IFunction) drawFunction();
        if (GraphBuilder.type instanceof ISystem) drawSystem();

        stage.setScene(scene);
        stage.show();
    }

    public void drawFunction() {
        IFunction function = (IFunction) GraphBuilder.type;
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        series.setName(function.toString());

        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series);

        for (double point = -10; point <= 10; point += 0.01) {
            series.getData().add(new XYChart.Data<>(point, function.solveForX(point)));
        }
        XYChart.Series<Double, Double> series2 = new XYChart.Series<>();
        series2.setName("Result: [" + point.getX() + " ; " + point.getY() + "]");
        series2.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
        lineChart.getData().add(series2);
    }

    public void drawSystem() {
        ISystem system = (ISystem) GraphBuilder.type;
        String[] titles = system.toString().replace("[", "").replace("]", "").split(" ; ");
        XYChart.Series<Double, Double> series1 = new XYChart.Series<>();
        series1.setName(titles[0]);
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series1);
        for (double point = -10; point <= 10; point += 0.01) {
            series1.getData().add(new XYChart.Data<>(point, system.getDraw().get(0).apply(point)));
        }
        XYChart.Series<Double, Double> series2 = new XYChart.Series<>();
        series2.setName(titles[1]);
        lineChart.getData().add(series2);
        for (double point = -10; point <= 10; point += 0.01) {
            series2.getData().add(new XYChart.Data<>(point, system.getDraw().get(1).apply(point)));
        }
        XYChart.Series<Double, Double> series3 = new XYChart.Series<>();
        series3.setName("Result: [" + point.getX() + ";" + point.getY() + "]");
        series3.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
        lineChart.getData().add(series3);
    }
}
