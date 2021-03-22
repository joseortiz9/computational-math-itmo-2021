package ru.ifmo.cmath;

import ru.ifmo.cmath.commands.*;
import ru.ifmo.cmath.exceptions.NotCommandFoundException;

import java.util.HashMap;

public class CommandMapper {
    private final HashMap<String, ICommand> commands;
    public CommandMapper() {
        commands = new HashMap<>();
        commands.put("set_accuracy", new SetAccuracy());
        commands.put("exit", new Exit());
        commands.put("help", new Help());
        commands.put("solve_matrix", new SolveMatrix());
    }

    public ICommand findCommand(String input) {
        ICommand c = commands.get(input);
        if (c == null) throw new NotCommandFoundException();

        return c;
    }

    public HashMap<String, ICommand> getCommands() {
        return commands;
    }
}
