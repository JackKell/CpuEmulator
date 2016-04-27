package com.github.jackkell.cpuemulator.util;

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
