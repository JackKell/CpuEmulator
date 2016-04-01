package com.github.jackkell.cpuemulator.cpu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
    static private String COMMAND_REGEX =  "^([a-z]+)(?:\\s+([^,]+))?(?:,\\s+([^,]+))?(?:,\\s+([^,]+))?$";
    private String raw;
    private String operation;
    private List<String> arguments;

    public Command(String raw) throws Exception {
        Pattern pattern = Pattern.compile(COMMAND_REGEX);
        Matcher matcher = pattern.matcher(raw);
        if (matcher.matches()) {
            this.raw = raw;
            this.operation = matcher.group(1);
            if (matcher.groupCount() > 1) {
                List<String> arguments = new ArrayList<>();
                for(int currentArgumentIndex = 1; currentArgumentIndex < matcher.groupCount(); currentArgumentIndex++) {
                    arguments.add(matcher.group(currentArgumentIndex));
                }
                this.arguments = arguments;
            } else {
                this.arguments = new ArrayList<>();
            }
        } else {
            throw new Exception();
        }
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
