package com.github.jackkell.cpuemulator.util;

import com.github.jackkell.cpuemulator.cpu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
The Command class represents commands given by the user. For example "mov eax, 7" would be a command. Commands can be
executed to affect the parts of the cpu.
 */
public class Command {
    // A regex string that matches the command format to a user's input
    // Check out: https://regex101.com/
    // to learn more about regex and try out this command.
    static private String COMMAND_REGEX =  "^([a-z]+)(?:\\s+([^,]+))?(?:,\\s+([^,]+))?(?:,\\s+([^,]+))?$";
    // The string given by the user
    private String raw;
    // The command that the user wants to run
    private Operation operation;
    // The list of arguments that the user has given
    private List<CommandArg> arguments;

    public Command(String raw) throws Exception {
        Pattern pattern = Pattern.compile(COMMAND_REGEX);
        Matcher matcher = pattern.matcher(raw);
        // If the command matches the regex then it is a validly formatted command
        if (matcher.matches()) {
            this.raw = raw;
            // Check if the given operation is supported
            if (Operation.contains(matcher.group(1))) {
                this.operation = Operation.valueOf(matcher.group(1));
            }
            // If not supported throw exception
            else {
                throw new Exception("No such supported operation " + matcher.group(1) + ".");
            }

            // Check if the matcher has more that just one one match
            if (matcher.groupCount() > 1) {
                List<CommandArg> arguments = new ArrayList<>();
                // Iterate through all of the groups starting at the 3rd value (single values don't start util the 3rd value)
                for(int currentArgumentIndex = 2; currentArgumentIndex <= matcher.groupCount(); currentArgumentIndex++) {
                    String argumentValue = matcher.group(currentArgumentIndex);
                    if (argumentValue == null) {
                        break;
                    }
                    // declare a new command argument
                    CommandArg commandArg;

                    // Check if the argument is a register
                    if (Registers.contains(argumentValue)) {
                        RegisterArg registerArg = new RegisterArg(Registers.valueOf(argumentValue));
                        arguments.add(registerArg);
                        continue;
                    }

                    // Check if the argument is a constant
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

                    // Check if the argument is in memory
                    MemoryValue memoryValue = Memory.memory.get(argumentValue);
                    if (memoryValue != null) {
                        MemoryArg memoryArg = new MemoryArg(argumentValue);
                        arguments.add(memoryArg);
                        continue;
                    }

                    // if none of the other options have occurred make the value a string argument
                    arguments.add(new StringArg(argumentValue));
                }
                this.arguments = arguments;
            } else {
                this.arguments = new ArrayList<>();
            }
        }
    }

    // Run the command in the control unit
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
