package ru.ifmo.cmath;

import ru.ifmo.cmath.algebra.*;
import ru.ifmo.cmath.graphing.GraphBuilder;
import ru.ifmo.cmath.utils.Point;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final static Double[] DEFAULT_X_POINTS = new Double[] {0.3, -2.554, 12.00321, 3.1 };

    private final Scanner scanner = new Scanner(System.in);
    private final MathFunctions mathFunctions = new MathFunctions();

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        print("+——————————————————————————————————+\n" +
                      "│    Полином Лагранжа и Чебышева   │\n" +
                      "│  на Приближение сложной функции  │\n" +
                      "+——————————————————————————————————+\n");

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

                double lower = 0, upper = 0;
                int numNodes = 21;
                print("a: "); lower = scanner.nextDouble();
                print("b: "); upper = scanner.nextDouble();
                print("№ nodes: "); numNodes = scanner.nextInt();

                if (lower > upper) {
                    double t = upper;
                    upper = lower;
                    lower = t;
                }

                ChebyshevPolynomial chebyshevPolynomial = new ChebyshevPolynomial(numNodes);
                List<Double> xDataWithChebyshev = chebyshevPolynomial.nodesBetween(lower, upper);
                List<Double> usualXData = nodesBetween(numNodes, lower, upper);

                List<Point> axisData = xDataWithChebyshev.stream()
                        .map(x -> new Point(x, chosenFunc.apply(x))).collect(Collectors.toList());

                Function lagrangePolynomial = new LagrangePolynomial().setAxisData(axisData).build();

                printResult(axisData);

                GraphBuilder.setData(chosenFunc, lagrangePolynomial, axisData, lower, upper);
                new GraphBuilder().run();
                exit();

            } catch (Throwable e) {
                print(e.getMessage()+"\n");
            }
            print("\n");
        }
    }

    private void printResult(List<Point> axisData) {
        int size = String.valueOf(axisData.size()).length();
        String line = String.join("", Collections.nCopies(size + 27, "-"));
        /* Build a header of the tabular */
        StringBuilder builder = new StringBuilder(
                String.format("+%s+\n| %" + size + "s |    AxisX   |    AxisY   |\n+%s+\n", line, "№i", line)
        );
        /* Build a result tabular */
        for (int i = 1; i <= axisData.size(); i++) {
            builder.append(String.format("| %" + size + "s | % -2.6f | % -2.6f |\n", i,  axisData.get(i - 1).getX(), axisData.get(i - 1).getY()));
        }
        /* Print a result */
        System.out.println(builder.append(String.format("+%s+\n", line)));
    }

    private List<Double> nodesBetween(int numNodes, double lower, double upper) {
        if (numNodes < 2) {
            throw new IllegalArgumentException("Number of nodes can not be less than 2");
        }
        List<Double> nodes = new ArrayList<>(numNodes);

        /* Calculate nodes step */
        double step = (upper - lower) / (numNodes - 1);
        for (int i = 0; i < numNodes; i++) {
            nodes.add(lower + i * step);
        }
        return nodes;
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
