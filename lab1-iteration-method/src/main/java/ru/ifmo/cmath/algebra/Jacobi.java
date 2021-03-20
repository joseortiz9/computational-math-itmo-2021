package ru.ifmo.cmath.algebra;

import java.util.Arrays;

public class Jacobi {

    private double[][] matrix;
    private final int size;

    public Jacobi(double[][] matrix, int size) {
        this.matrix = matrix;
        this.size = size;
    }

    public boolean makeDominant() {
        boolean[] visited = new boolean[size];
        int[] rows = new int[size];

        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }

    public boolean transformToDominant(int r, boolean[] visited, int[] rows) {
        if (r == size) {
            double[][] T = new double[size][size+1];
            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < size + 1; j++)
                    T[i][j] = matrix[rows[i]][j];
            }
            matrix = T;
            return true;
        }

        for (int i = 0; i < size; i++) {
            if (visited[i]) continue;

            double sum = 0;

            for (int j = 0; j < size; j++)
                sum += Math.abs(matrix[i][j]);

            if (2 * Math.abs(matrix[i][r]) > sum) { // diagonally dominant?
                visited[i] = true;
                rows[r] = i;

                if (transformToDominant(r + 1, visited, rows))
                    return true;

                visited[i] = false;
            }
        }

        return false;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Jacobi solution:\n");
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
}
