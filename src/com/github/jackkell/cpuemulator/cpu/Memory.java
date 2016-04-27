package com.github.jackkell.cpuemulator.cpu;

import java.util.HashMap;
import java.util.Map;

public final class Memory {
    public static Map<String, MemoryValue> memory;
    static {
        memory = new HashMap<>();
    }
}
