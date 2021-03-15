package ru.ifmo.cmath;

import ru.ifmo.cmath.commands.CommandMapper;
import ru.ifmo.cmath.commands.ICommand;

import java.util.Scanner;

public class Main {

    private final CommandMapper commandMapper;
    private final IContext context;
    private final Scanner in;

    public Main() {
        commandMapper = new CommandMapper();
        in = new Scanner(System.in);
        context = new IContext() {
            int accuracy = 0;
            int sizeMatrix = 1;
            @Override
            public void print(String s) {
                System.out.print(s);
            }
            @Override
            public Scanner getReader() {
                return in;
            }
            @Override
            public void setAccuracy(int v) {
                this.accuracy = v;
            }
            @Override
            public void setSize(int v) {
                this.sizeMatrix = v;
            }
        };
    }

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        String input;
        ICommand command;

        context.print("+-------------------------------------------+\n" +
              "|        SIMPLE ITERATION METHOD            |\n" +
              "+-------------------------------------------+\n");

        while (context.getReader().hasNext()) {
            context.print("cmath-lab1$ ");
            input = context.getReader().nextLine();
            command = commandMapper.findCommand(input);

            if (command != null) command.execute(context);
            else context.print("Command not found, check 'help'!\n");
        }
    }


}
