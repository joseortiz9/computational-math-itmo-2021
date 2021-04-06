package ru.ifmo.cmath;

import ru.ifmo.cmath.algebra.IFunction;
import ru.ifmo.cmath.algebra.MathFunctions;
import ru.ifmo.cmath.algebra.NonLinearEqSolver;
import ru.ifmo.cmath.exceptions.NotCommandFoundException;
import ru.ifmo.cmath.graphing.GraphBuilder;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final NonLinearEqSolver solver = new NonLinearEqSolver();
    private final MathFunctions mathFunctions = new MathFunctions();

    public static void main(String[] args) {
        new Main().start();
        new GraphBuilder().run();
    }

    public void start() {
        print("+——————————————————————————————+\n" +
                "│           НУ И СНУ           │\n" +
                "+——————————————————————————————+\n");
        while (true) {
            print("(1) nonlinear equation\n");
            print("(2) system of nonlinear equations\n");
            print("(0) exit\n");
            try {
                switch (input()) {
                    case "1": readNonlinearEquation();
                        break;
                    case "2": readSystemOfEquations();
                        break;
                    case "0": exit();
                        break;
                    default: throw new NotCommandFoundException();
                }
            } catch (Throwable e) {
                print(e.getMessage()+"\n");
            }
        }
    }

    private void readAndSetAccuracy() {
        print("accuracy[0..1] or [-1] to use base value: ");
        double accuracy = Double.parseDouble(scanner.nextLine());
        if (accuracy <= 0 || accuracy >= 1 ) {
            solver.setAccuracy(NonLinearEqSolver.BASE_ACCURACY);
            print("Was set the base accuracy = " + NonLinearEqSolver.BASE_ACCURACY + "\n");
        } else {
            solver.setAccuracy(accuracy);
        }
    }

    private void readNonlinearEquation() {
        for (int i = 0; i < mathFunctions.getAvailableFunctions().size(); i++) {
            print("(%s) %s%n", i+1, mathFunctions.getAvailableFunctions().get(i));
        }
        solveNonlinearEquation(mathFunctions.getAvailableFunctions().get(Integer.parseInt(input())-1));
    }

    private void solveNonlinearEquation(IFunction function) {
        readAndSetAccuracy();

        print("Initial left guess: "); double a = scanner.nextDouble();
        print("Initial right guess: "); double b = scanner.nextDouble();

        //Graphic.setData(0, equations);

        print("Tangents method: ");
        try {
            Object[] result = solver.tangentsMethod(function, a, b);
            print("[x = %.18f, Δx = %.18f, iters = %d times]%n", result[0], result[1], result[2]);
        }
        catch (RuntimeException e) {
            print("%s%n", e.getMessage());
        }

        print("Secant method: ");
        try {
            Object[] result = solver.secantMethod(function, a, b);
            print("[x = %.18f, iters = %d times]%n", result[0], result[1]);
        }
        catch (RuntimeException e) {
            print("%s%n", e.getMessage());
        }
    }

    public void readSystemOfEquations () {

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