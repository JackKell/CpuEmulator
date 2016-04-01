package com.github.jackkell.cpuemulator.cpu;

import java.util.HashMap;
import java.util.Map;

public final class RegisterBank {
    public static Map<String, Register> generalRegisters = new HashMap<>();
    public static Map<String, Register> segmentRegisters = new HashMap<>();
    public static Register flagRegister = new Register(0L);
    static {
        // General Registers
        generalRegisters.put("rax", new Register(0L));
        generalRegisters.put("rbx", new Register(0L));
        generalRegisters.put("rcx", new Register(0L));
        generalRegisters.put("rdx", new Register(0L));

        generalRegisters.put("rbp", new Register(0L));
        generalRegisters.put("rsi", new Register(0L));
        generalRegisters.put("rdi", new Register(0L));
        generalRegisters.put("rsp", new Register(0L));
        generalRegisters.put("rip", new Register(0L));

        segmentRegisters.put("cs", new Register(0L));
        segmentRegisters.put("ds", new Register(0L));
        segmentRegisters.put("es", new Register(0L));
        segmentRegisters.put("fs", new Register(0L));
        segmentRegisters.put("gs", new Register(0L));
        segmentRegisters.put("ss", new Register(0L));
    }
}
