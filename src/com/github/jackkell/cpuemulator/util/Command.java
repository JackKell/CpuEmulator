package com.github.jackkell.cpuemulator.util;

import com.github.jackkell.cpuemulator.cpu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
    static private String COMMAND_REGEX =  "^([a-z]+)(?:\\s+([^,]+))?(?:,\\s+([^,]+))?(?:,\\s+([^,]+))?$";
    private String raw;
    private Operation operation;
    private List<CommandArg> arguments;

    public Command(String raw) throws Exception {
        Pattern pattern = Pattern.compile(COMMAND_REGEX);
        Matcher matcher = pattern.matcher(raw);
        if (matcher.matches()) {
            this.raw = raw;
            if (Operation.contains(matcher.group(1))) {
                this.operation = Operation.valueOf(matcher.group(1));
            } else {
                throw new Exception("No such supported operation " + matcher.group(1) + ".");
            }
            if (matcher.groupCount() > 1) {
                List<CommandArg> arguments = new ArrayList<>();
                for(int currentArgumentIndex = 2; currentArgumentIndex <= matcher.groupCount(); currentArgumentIndex++) {
                    String argumentValue = matcher.group(currentArgumentIndex);
                    if (argumentValue == null) {
                        break;
                    }
                    CommandArg commandArg;
                    if (Registers.contains(argumentValue)) {
                        RegisterArg registerArg = new RegisterArg(Registers.valueOf(argumentValue));
                        arguments.add(registerArg);
                        continue;
                    }

                    Long value;
                    try {
                        value = Long.parseLong(argumentValue);
                    } catch (NumberFormatException numberFormatException) {
                        value = null;
                    }
                    if (value != null) {
                        int size = 0;
                        String binaryValue = Long.toBinaryString(value);
                        if (binaryValue.length() <= 8) {
                            size = 8;
                        } else if (binaryValue.length() <= 16) {
                            size = 16;
                        } else if (binaryValue.length() <= 32) {
                            size = 32;
                        } else {
                            size = 64;
                        }
                        ConstantArg constantArg = new ConstantArg(size, value);
                        arguments.add(constantArg);
                        continue;
                    }

                    MemoryValue memoryValue = Memory.memory.get(argumentValue);
                    if (memoryValue != null) {
                        MemoryArg memoryArg = new MemoryArg(argumentValue);
                        arguments.add(memoryArg);
                        continue;
                    }

                    arguments.add(new StringArg(argumentValue));
                }
                this.arguments = arguments;
            } else {
                this.arguments = new ArrayList<>();
            }
        }
    }

    public void execute() throws Exception {
        ControlUnit.runCommand(this);
    }

    public String getRaw() {
        return raw;
    }

    public Operation getOperation() {
        return operation;
    }

    public List<CommandArg> getArguments() {
        return arguments;
    }
}
