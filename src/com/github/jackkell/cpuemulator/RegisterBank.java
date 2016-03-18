package com.github.jackkell.cpuemulator;

import java.util.HashMap;
import java.util.Map;

public final class RegisterBank {
    public static Map<String, Register> generalRegisters = new HashMap<>();
    public static Map<String, Short> segmentRegisters = new HashMap<>();
    public static Map<String, Short> flagRegisters = new HashMap<>();
    //public static Map<String, Integer> eflags = new HashMap<>();
    static {
        // General Registers
        generalRegisters.put("rax", new Register(1000L));
        generalRegisters.put("rbx", new Register(1337L));
        generalRegisters.put("rcx", new Register(0L));
        generalRegisters.put("rdx", new Register(0L));

        generalRegisters.put("rbp", new Register(5L));
        generalRegisters.put("rsi", new Register(0L));
        generalRegisters.put("rdi", new Register(0L));
        generalRegisters.put("rsp", new Register(0L));
        generalRegisters.put("rip", new Register(0L));

        segmentRegisters.put("cs", (short) 0);
        segmentRegisters.put("ds", (short) 0);
        segmentRegisters.put("es", (short) 0);
        segmentRegisters.put("fs", (short) 0);
        segmentRegisters.put("gs", (short) 0);
        segmentRegisters.put("ss", (short) 0);

        flagRegisters.put("cf", (short) 0);
        flagRegisters.put("pf", (short) 0);
        flagRegisters.put("af", (short) 0);
        flagRegisters.put("zf", (short) 0);
        flagRegisters.put("sf", (short) 0);
        flagRegisters.put("tf", (short) 0);
        flagRegisters.put("if", (short) 0);
        flagRegisters.put("df", (short) 0);
        flagRegisters.put("of", (short) 0);

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
