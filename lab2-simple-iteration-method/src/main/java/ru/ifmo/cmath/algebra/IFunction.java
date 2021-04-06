package ru.ifmo.cmath.algebra;

import java.util.function.Function;

/**
 * Basic function implementation.
 */
public interface IFunction {
   Function<Double, Double> getFunction();
   default double solveForX(double x) {
      return getFunction().apply(x);
   }
}
