package com.github.jackkell.cpuemulator.util;

import com.github.jackkell.cpuemulator.cpu.Memory;
import com.github.jackkell.cpuemulator.cpu.MemoryValue;

public class MemoryArg extends CommandArg {
    private final String name;
    private final long value;

    public MemoryArg(String name) {
        super(0);
        MemoryValue memoryValue =  Memory.memory.get(name);
        this.size = memoryValue.size;
        this.name = name;
        this.value = memoryValue.value;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }
}
