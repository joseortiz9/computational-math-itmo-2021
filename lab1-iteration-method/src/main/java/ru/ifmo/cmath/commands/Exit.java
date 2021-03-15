package ru.ifmo.cmath.commands;

import ru.ifmo.cmath.IContext;

public class Exit implements ICommand {
    @Override
    public void execute(IContext context) {
        context.getReader().close();
        context.print("Thanks for trying me :)");
        System.exit(0);
    }
}
