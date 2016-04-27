package com.github.jackkell.cpuemulator.util;

/*
A StringArg is a type of CommandArg that is used to represent a string value being passed into the argument list
 */
public class StringArg extends CommandArg {
    private String value;

    public StringArg(String value) {
        super(0);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
