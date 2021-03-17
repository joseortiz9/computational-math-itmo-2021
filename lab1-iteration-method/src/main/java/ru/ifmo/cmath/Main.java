package ru.ifmo.cmath;

import ru.ifmo.cmath.commands.ICommand;
import ru.ifmo.cmath.exceptions.NotCommandFoundException;
import ru.ifmo.cmath.exceptions.NotFileFoundException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private final CommandMapper commandMapper;
    private final IContext context;
    private final Scanner in;

    public Main() {
        commandMapper = new CommandMapper();
        in = new Scanner(System.in);
        context = new IContext() {
            double accuracy = 0.1D;
            @Override
            public void print(String s) {
                System.out.print(s);
            }
            @Override
            public Scanner getReader() {
                return in;
            }
            @Override
            public void setAccuracy(double v) {
                this.accuracy = v;
            }
            @Override
            public double getAccuracy() {
                return accuracy;
            }
        };
    }

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        String[] input;
        ICommand command;

        context.print("+-------------------------------------------+\n" +
              "|        SIMPLE ITERATION METHOD            |\n" +
              "+-------------------------------------------+\n");
        context.print("Default accuracy=0.1D\n");

        while (true) {
            context.print("cmath-lab1$ ");
            input = context.getReader().nextLine().trim().split("(\\s++)");

            try {
                command = commandMapper.findCommand(input[0].toLowerCase());
                command.execute(context, input);
            } catch (NotCommandFoundException e) {
                context.print("Command not found, check 'help'!\n");
            }catch (NotFileFoundException e) {
                context.print("Such file doesnt exist\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                context.print("Entered wrong args...\n");
            } catch (NumberFormatException e) {
                context.print("Entered number has wrong format\n");
            }
        }
    }


}
