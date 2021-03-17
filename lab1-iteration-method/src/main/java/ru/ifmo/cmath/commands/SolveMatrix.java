package ru.ifmo.cmath.commands;

import ru.ifmo.cmath.IContext;
import ru.ifmo.cmath.exceptions.NotFileFoundException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class SolveMatrix implements ICommand {
    private int size = -1;
    private double[][] matrix;

    @Override
    public void execute(IContext context, String[] args) {
        if (args.length == 1) {
            context.print("Enter the size of the matrix: ");
            size = Integer.parseInt(context.getReader().nextLine());
            readFromConsole(context.getReader());
        } else if(args[1].equals("-f")) {
            context.print("Enter the size of the matrix: ");
            size = Integer.parseInt(context.getReader().nextLine());
            context.print("Reading matrix from file "+ args[2] +"...\n");
            readFromFile(args[2]);
        } else if (args[1].equals("-s")) {
            size = Integer.parseInt(args[2]);
            readFromConsole(context.getReader());
        }
        if (size < 1 || size > 20) {
            throw new NumberFormatException();
        }


        System.out.println(Arrays.deepToString(matrix));
    }

    public void readFromConsole(Scanner in) {
        matrix = new double[size][size+1];
        int rows = 0;
        reader:
        while(in.hasNextLine()) {
            for (int i=0; i<matrix.length; i++) {
                String[] line = in.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    matrix[i][j] = Double.parseDouble(line[j].replace(",","."));
                }
                rows++;
                if (rows == size) break reader;
            }
        }
    }

    public void readFromFile(String filePath) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));
            readFromConsole(sc);
        } catch (FileNotFoundException e) {
            throw new NotFileFoundException();
        }
    }
}
