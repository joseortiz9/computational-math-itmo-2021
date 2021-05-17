package ru.ifmo.cmath;

import ru.ifmo.cmath.algebra.DifferentialEqSolver;
import ru.ifmo.cmath.algebra.Function;
import ru.ifmo.cmath.algebra.LagrangePolynomial;
import ru.ifmo.cmath.algebra.MathFunctions;
import ru.ifmo.cmath.graphing.GraphBuilder;
import ru.ifmo.cmath.utils.Point;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private final static Double[] DEFAULT_X_POINTS = new Double[] {0.3, -2.554, 12.00321, 3.1 };

    private final Scanner scanner = new Scanner(System.in);
    private final MathFunctions mathFunctions = new MathFunctions();
    private final DifferentialEqSolver solver = new DifferentialEqSolver();

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        print("+———————————————————————————————+\n" +
                      "│         Решение ОДУ           │\n" +
                      "+———————————————————————————————+\n");

        while (true) {
            print("Choose a function:\n");
            try {
                for (int i = 0; i < mathFunctions.getAvailableFunctions().size(); i++) {
                    print("(%s) %s%n", i+1, mathFunctions.getAvailableFunctions().get(i));
                }
                print("(0) exit\n");
                int optionSelected = Integer.parseInt(input());
                if (optionSelected == 0) exit();

                Function chosenFunc = mathFunctions.getAvailableFunctions().get(optionSelected-1);

                double x0 = 0, y0 = 0, end = 0, step = 0.001;
                print("x_0: "); x0 = scanner.nextDouble();
                print("y_0: "); y0 = scanner.nextDouble();
                print("last X value: "); end = scanner.nextDouble();
                Point initialPoint = new Point(x0, y0);

                readAndSetAccuracy();
                boolean customStep = readStep();

                List<Point> axisData = solver.solveByMilne(chosenFunc, initialPoint, end);
                printResult(axisData);
                Function lagrangianPolynomial = null;
                try {
                    lagrangianPolynomial = new LagrangePolynomial().setAxisData(axisData).build();
                } catch (Throwable e) {
                    print(e.getMessage()+"\n");
                }
                if (lagrangianPolynomial == null)
                    GraphBuilder.setData(chosenFunc, axisData);
                else
                    GraphBuilder.setData(chosenFunc, lagrangianPolynomial);
                new GraphBuilder().run();
                exit();

            } catch (Throwable e) {
                print(e.getMessage()+"\n");
            }
            print("\n");
        }
    }

    private void printResult(List<Point> points) {
        int size = String.valueOf(points.size()).length();
        String line = String.join("", Collections.nCopies(size + 44, "-"));
        /* Build a header of the tabular */
        StringBuilder builder = new StringBuilder(
                String.format("+%s+\n| %" + size + "s |       Axis X       |       Axis Y       |\n+%s+\n", line, "№i", line)
        );
        /* Build a result tabular */
        for (int i = 1; i <= points.size(); i++) {
            builder.append(String.format("| %" + size + "s | %018.9f | %018.9f |\n", i,  points.get(i - 1).getX(), points.get(i - 1).getY()));
        }
        /* Print a result */
        System.out.println(builder.append(String.format("+%s+\n", line)));
    }

    private void readAndSetAccuracy() {
        print("accuracy[0..1] or [-1] to use base value: ");
        double accuracy = scanner.nextDouble();
        if (accuracy <= 0 || accuracy >= 1) {
            solver.setAccuracy(DifferentialEqSolver.BASE_ACCURACY);
            print("Was set the base accuracy = " + DifferentialEqSolver.BASE_ACCURACY + "\n");
        } else {
            solver.setAccuracy(accuracy);
        }
    }

    private boolean readStep() {
        print("step[0..1] or [-1] to use base calculated value: ");
        double step = scanner.nextDouble();
        if (step <= 0 || step >= 1 ) {
            print("Was set the base accuracy... \n");
            return true;
        } else {
            solver.setStep(step);
            return false;
        }
    }

    private static void print(String pattern, Object... args) {
        System.out.printf(pattern, args);
    }

    private static void exit() {
        System.exit(0);
    }

    private String input() {
        print("> ");
        return scanner.nextLine().trim().toLowerCase(Locale.ROOT);
    }

}
