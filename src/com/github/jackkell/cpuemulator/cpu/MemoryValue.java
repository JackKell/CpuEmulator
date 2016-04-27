package com.github.jackkell.cpuemulator.cpu;


public class MemoryValue {
    public final int size;
    public long value;

    public MemoryValue(int size) {
        this(size, 0);
    }

    public MemoryValue(int size, long value) {
        this.size = size;
        this.value = value;
    }
}
