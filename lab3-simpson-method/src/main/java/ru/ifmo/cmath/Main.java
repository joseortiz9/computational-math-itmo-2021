package ru.ifmo.cmath;

import ru.ifmo.cmath.algebra.*;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final IntegralSolver solver = new IntegralSolver();
    private final MathFunctions mathFunctions = new MathFunctions();

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        print("+——————————————————————————————+\n" +
                      "│   Численное интегрирование   │\n" +
                      "+——————————————————————————————+\n");

        while (true) {
            print("Choose a function to integrate by Simpsons rule:\n");
            try {
                for (int i = 0; i < mathFunctions.getAvailableFunctions().size(); i++) {
                    print("(%s) %s%n", i+1, mathFunctions.getAvailableFunctions().get(i));
                }
                print("(0) exit\n");
                int optionSelected = Integer.parseInt(input());
                if (optionSelected == 0) exit();

                readAndSetAccuracy();

                IFunction chosenFunc = mathFunctions.getAvailableFunctions().get(optionSelected-1);
                double lower = -5, upper = 5;
                print("Lower limit of integration: "); lower = scanner.nextDouble();
                print("Upper limit of integration: "); upper = scanner.nextDouble();

                boolean hasSignChange = false;
                if (lower > upper) {
                    double t = upper;
                    upper = lower;
                    lower = t;
                    hasSignChange = true;
                }

                IntegralAnswer answer = null;
                if ((Double.isNaN(chosenFunc.solveForX(0)) || Double.isInfinite(chosenFunc.solveForX(0)))
                        && lower == 0 || upper == 0) {
                    if (lower == 0 && upper > 0) {
                        lower += 0.000001;
                        answer = solver.bySimpsonsRule(new Integral(chosenFunc).from(lower).to(upper));
                    } else if (upper == 0 && lower < 0) {
                        upper -= 0.000001;
                        answer = solver.bySimpsonsRule(new Integral(chosenFunc).from(lower).to(upper));
                    }
                    if ((lower < 0 && upper > 0)) {
                        lower -= 0.000001;
                        upper += 0.000001;
                        //before zero
                        answer = solver.bySimpsonsRule(new Integral(chosenFunc).from(lower).to(-0.001));
                        //after zero with calculated value
                        double ans2 = Double.sum(answer.getResult(), solver.bySimpsonsRule(new Integral(chosenFunc).from(0.001).to(upper)).getResult());
                        answer.setResult(ans2);
                    }
                } else {
                    answer = solver.bySimpsonsRule(new Integral(chosenFunc).from(lower).to(upper));
                }

                if (hasSignChange)
                    answer.setResult(-1d * answer.getResult());

                print("Area =  %.18f\n", answer.getResult());
                print("Divisions =  %d parts\n", answer.getParts());
                print("Error margin =  %.18f\n", answer.getError());
                if(Double.isNaN(answer.getResult()) || Double.isInfinite(answer.getResult())) {
                    print("There is an endpoint that doesn't belong to the domain of the function. \nThus, the integral cannot be approximated.\n");
                }
                print("\n");

            } catch (Throwable e) {
                print(e.getMessage()+"\n");
            }
        }
    }

    private void readAndSetAccuracy() {
        print("accuracy[0..1] or [-1] to use base value: ");
        double accuracy = Double.parseDouble(scanner.nextLine());
        if (accuracy <= 0 || accuracy >= 1 ) {
            solver.setAccuracy(IntegralSolver.BASE_ACCURACY);
            print("Was set the base accuracy = " + IntegralSolver.BASE_ACCURACY + "\n");
        } else {
            solver.setAccuracy(accuracy);
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
