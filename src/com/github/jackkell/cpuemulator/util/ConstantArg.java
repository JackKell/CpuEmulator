package com.github.jackkell.cpuemulator.util;

public class ConstantArg extends CommandArg {
    private long value;

    public ConstantArg(int size, long value) {
        super(size);
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
