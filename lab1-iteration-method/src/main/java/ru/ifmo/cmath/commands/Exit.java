package ru.ifmo.cmath.commands;

import ru.ifmo.cmath.IContext;

public class Exit implements ICommand {
    @Override
    public void execute(IContext context, String[] args) {
        context.getReader().close();
        context.print("Thanks for trying me :)\n");
        System.exit(0);
    }
}
