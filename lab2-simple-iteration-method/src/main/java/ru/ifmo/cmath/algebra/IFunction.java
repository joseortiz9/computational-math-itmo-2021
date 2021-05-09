package ru.ifmo.cmath.algebra;

import java.util.function.Function;

/**
 * Basic function implementation.
 * Derive methods taken from:
 * https://github.com/bnorm/java-math/blob/abde77a75bc7bf2f1fb6ac9c80c9205eb9af285e/src/math/calculus/Differentiation.java#L27
 * https://github.com/Kanunnikoff/mathematics/blob/master/src/org/itmuslim/science/mathematics/algebra/numericalmethod/equation/Equation.java
 */
public interface IFunction {
   Function<Double, Double> getFunction();
   int getAmountOfRoots();
   default double getDeltaForX(double x) {
      return Math.max(Math.abs(x / 1000.0), 0.0001);
   }
   default double solveForX(double x) {
      return getFunction().apply(x);
   }
   default double deriveForX(double x) {
      double delta = getDeltaForX(x);
      return (getFunction().apply(x + delta) - getFunction().apply(x - delta)) / (2 * delta);
   }
   default double secondDeriveForX(double x) {
      double delta = getDeltaForX(x);
      return 1.0 / (delta * delta) * (getFunction().apply(x + delta) - 2.0 * getFunction().apply(x) + getFunction().apply(x - delta));
   }
}
