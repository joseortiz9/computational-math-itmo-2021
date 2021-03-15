package ru.ifmo.cmath.commands;

import java.util.HashMap;

public class CommandMapper {
    private final HashMap<String, ICommand> commands;
    public CommandMapper() {
        commands = new HashMap<>();
        commands.put("set_accuracy", new SetAccuracy());
        commands.put("exit", new Exit());
    }

    public ICommand findCommand(String input) {
        return commands.get(input);
    }

    public HashMap<String, ICommand> getCommands() {
        return commands;
    }
}
