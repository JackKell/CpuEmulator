package com.github.jackkell.cpuemulator.cpu;

import com.github.jackkell.cpuemulator.util.MemoryValue;

import java.util.HashMap;
import java.util.Map;

public final class Memory {
    public static Map<String, MemoryValue> memory;
    static {
        memory = new HashMap<>();
    }
}
