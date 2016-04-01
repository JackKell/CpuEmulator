package com.github.jackkell.cpuemulator.cpu;

import java.util.List;

public class Command {
    private String raw;
    private String operation;
    private List<String> arguments;

    public Command(String raw, String operation, List<String> arguments) {
        this.raw = raw;
        this.operation = operation;
        this.arguments = arguments;
    }

    public void execute() {
        ControlUnit.runCommand(this);
    }

    public String getRaw() {
        return raw;
    }

    public String getOperation() {
        return operation;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
