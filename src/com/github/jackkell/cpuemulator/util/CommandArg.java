package com.github.jackkell.cpuemulator.util;

/*
A command argument is used to extend any class that will represent information passed by the user in a command's
argument list.
 */
public abstract class CommandArg {
    int size;

    CommandArg(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
