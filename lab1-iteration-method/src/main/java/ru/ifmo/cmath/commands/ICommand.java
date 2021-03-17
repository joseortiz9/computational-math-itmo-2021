package ru.ifmo.cmath.commands;

import ru.ifmo.cmath.IContext;

public interface ICommand {
    void execute(IContext context, String[] args);
}
