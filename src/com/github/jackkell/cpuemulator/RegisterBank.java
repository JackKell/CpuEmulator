package com.github.jackkell.cpuemulator;

import java.util.HashMap;
import java.util.Map;

public final class RegisterBank {
    public static Map<String, Register> generalRegisters = new HashMap<>();
    public static Map<String, Register> segmentRegisters = new HashMap<>();
    public static Map<String, Register> flagRegisters = new HashMap<>();
    //public static Map<String, Integer> eflags = new HashMap<>();
    static {
        // General Registers
        generalRegisters.put("rax", new Register(0L));
        generalRegisters.put("rbx", new Register(0L));
        generalRegisters.put("rcx", new Register(0L));
        generalRegisters.put("rdx", new Register(0L));

        generalRegisters.put("rbp", new Register(5L));
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

        flagRegisters.put("cf", new Register(0L));
        flagRegisters.put("pf", new Register(0L));
        flagRegisters.put("af", new Register(0L));
        flagRegisters.put("zf", new Register(0L));
        flagRegisters.put("sf", new Register(0L));
        flagRegisters.put("tf", new Register(0L));
        flagRegisters.put("if", new Register(0L));
        flagRegisters.put("df", new Register(0L));
        flagRegisters.put("of", new Register(0L));

    }
    /*
    // TODO: Decide to include these or not
    // EFlags
    public static int rf = 0;
    public static int vm = 0;
    public static int ac = 0;
    public static int vif = 0;
    public static int vip = 0;
    public static int id = 0;
    */
}
