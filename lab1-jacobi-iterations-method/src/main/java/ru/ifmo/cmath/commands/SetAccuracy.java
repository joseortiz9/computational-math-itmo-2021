package ru.ifmo.cmath.commands;

import ru.ifmo.cmath.IContext;

public class SetAccuracy implements ICommand {
    @Override
    public void execute(IContext context, String[] args) {
        if (args.length > 1) {
            context.setAccuracy(Double.parseDouble(args[1]));
        } else {
            context.print("Enter the new accuracy value: ");
            context.setAccuracy(Double.parseDouble(context.getReader().nextLine()));
        }
        context.print("New accuracy=" + context.getAccuracy() + "D\n");
    }
}
