package com.github.jackkell.cpuemulator.util;

public abstract class CommandArg {
    int size;

    CommandArg(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
