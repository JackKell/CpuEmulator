package com.github.jackkell.cpuemulator.util;

/*
Is a data package that hold the value to the memory hash table. When a key is given to the memory hash table it returns
a MemoryValue that contains both a size and a value.
 */
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
