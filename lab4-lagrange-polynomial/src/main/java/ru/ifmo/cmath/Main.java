package ru.ifmo.cmath;

import ru.ifmo.cmath.algebra.IFunction;
import ru.ifmo.cmath.algebra.LagrangePolynomial;
import ru.ifmo.cmath.algebra.MathFunctions;
import ru.ifmo.cmath.graphing.GraphBuilder;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private final static Double[] DEFAULT_X_POINTS = new Double[] {0.3, -5.554, 21.41, 3.1 };

    private final Scanner scanner = new Scanner(System.in);
    private final MathFunctions mathFunctions = new MathFunctions();

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        print("+———————————————————————————————+\n" +
                "│ Интерполяция методом Лагранжа │\n" +
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

                IFunction chosenFunc = mathFunctions.getAvailableFunctions().get(optionSelected-1);
                print("Default XPoints:\n");
                print(Arrays.toString(DEFAULT_X_POINTS));
                print("\n");

                Double[] yPointsChosen = new Double[DEFAULT_X_POINTS.length];
                for (int i = 0; i < DEFAULT_X_POINTS.length; i++) {
                    double yValue = chosenFunc.solveForX(DEFAULT_X_POINTS[i]);
                    if (Double.isNaN(yValue) || Double.isInfinite(yValue)) {
                        throw new IllegalArgumentException("Original Function is undefined at x=" + DEFAULT_X_POINTS[i]);
                    }
                    yPointsChosen[i] = yValue;
                }

                print("Generating lagrange function...\n");
                IFunction lagrangePolynomial = new LagrangePolynomial(DEFAULT_X_POINTS, yPointsChosen).buildFunction();
                if (lagrangePolynomial.getFunction() == null) throw new IllegalArgumentException("Not possible to generate the lagrange function!");
                print("\n");

                GraphBuilder.setData(chosenFunc, lagrangePolynomial, DEFAULT_X_POINTS, yPointsChosen);
                new GraphBuilder().run();
                exit();

            } catch (Throwable e) {
                print(e.getMessage()+"\n");
            }
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
