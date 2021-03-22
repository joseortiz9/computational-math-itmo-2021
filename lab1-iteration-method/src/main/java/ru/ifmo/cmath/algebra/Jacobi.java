package ru.ifmo.cmath.algebra;

public class Jacobi {
    private final static int MAX_PERMUTATIONS = 100;

    private double[][] matrix;
    private double[][] diagDomMatrix;
    private final int size;

    private double[] rootsSolution;
    private double[] errorMargins;
    private int iterations;

    public Jacobi(double[][] matrix, int size) {
        this.matrix = matrix;
        this.diagDomMatrix = matrix;
        this.size = size;
        iterations = 1;
    }

    public boolean makeDominant() {
        int permutations = 0;
        while (MAX_PERMUTATIONS > permutations) {
            if (isDiagonallyDominant()) {
                matrix = diagDomMatrix;
                return true;
            } else {
                permuteRows();
                permutations++;
            }
        }
        return false;
    }

    public boolean isDiagonallyDominant() {
        for (int i = 0; i < size; i++) {
            int sumRow = 0;
            for (int j = 0; j < size; j++)
                sumRow += Math.abs(diagDomMatrix[i][j]);

            // removing the diagonal element.
            sumRow -= Math.abs(diagDomMatrix[i][i]);

            // checking if diagonal element is less than sum of non-diagonal element.
            if (Math.abs(diagDomMatrix[i][i]) < sumRow)
                return false;
        }
        return true;
    }

    public void permuteRows() {

    }


    public double get(int row, int column) {
        return matrix[row][column];
    }

    public double getFreeMember(int row) {
        return matrix[row][size];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Jacobi solution:\n");
        s.append("+——————————+——————————————————————+——————————————————————+\n")
         .append("│ variable │ value                │ Error Margin         │\n")
         .append("+——————————+——————————————————————+——————————————————————+\n");
        for (int i = 0; i < rootsSolution.length; i++) {
            s.append(String.format("│   x_%02d   │ %-20.16f │ % -20.16f │", i+1, rootsSolution[i], errorMargins[i])).append("\n");
        }
        s.append("+——————————+——————————————————————+——————————————————————+\n");
        s.append("A total of ").append(iterations).append(" iterations made.\n");
        return s.toString();
    }

    public String printSystem() {
        StringBuilder s = new StringBuilder();
        s.append("System of Equations:\n");
        for (double[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                if (j != row.length - 1)
                    s.append(row[j]).append(" * ").append("x_").append(j+1).append((j == row.length-2) ? " = " : " + ");
                else
                    s.append(row[j]);
            }
            s.append("\n");
        }
        return s.toString();
    }

    public String getMatrixAsString() {
        StringBuilder s = new StringBuilder();
        s.append("Matrix:\n");
        for (double[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                s.append(row[j]).append(" ");
                if (j == row.length - 2) s.append("|").append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public double[][] getMatrix() {
        return matrix;
    }
    public int getSize() {
        return size;
    }
    public double[] getRootsSolution() {
        return rootsSolution;
    }
    public double[] getErrorMargins() {
        return errorMargins;
    }
    public int getIterations() {
        return iterations;
    }
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
    public void setErrorMargins(double[] errorMargins) {
        this.errorMargins = errorMargins;
    }
    public void setRootsSolution(double[] rootsSolution) {
        this.rootsSolution = rootsSolution;
    }
}
