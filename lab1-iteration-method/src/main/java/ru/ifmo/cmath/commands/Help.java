package ru.ifmo.cmath.commands;

import ru.ifmo.cmath.IContext;

public class Help implements ICommand {
    @Override
    public void execute(IContext context, String[] args) {
        context.print("" +
                " set_accuracy   [value]  Set the accuracy for the system solver.    \n" +
                " solve_matrix            Read a linear equation system as a matrix. \n" +
                "           -f    [path]  Read matrix from file.                     \n" +
                "                         Example: solve_matrix -f in.txt            \n" +
                "           -s    [size]  Read with determinate size. M[s][s+1]      \n" +
                "                         Example: solve_matrix -s 4                 \n" +
                "                         size bigger than 0 and smaller 20          \n" +
                " exit                    terminate the application.                 \n" +
                " help                    Print the commands list.                   \n"
        );
    }
}
