package com.github.jackkell.cpuemulator.cpu;

import java.util.HashMap;
import java.util.Map;

import static com.github.jackkell.cpuemulator.cpu.RegisterBank.Reg.*;
import static java.lang.String.format;

public final class RegisterBank {

    public enum Reg {
        rax("rax", 64), rbx("rbx", 64), rcx("rcx", 64),
        rdx("rdx", 64), rbp("rbp", 64), rsi("rsi", 64),
        rdi("rdi", 64), rsp("rsp", 64), rip("rip", 64),

        eax("rax", 32), ebx("rbx", 32), ecx("rcx", 32),
        edx("rdx", 32), ebp("rbp", 32), esi("rsi", 32),
        edi("rdi", 32), esp("rsp", 32), eip("rip", 32),

        ax("rax", 16), bx("rbx", 16), cx("rcx", 16),
        dx("rdx", 16), bp("rbp", 16), si("rsi", 16),
        di("rdi", 16), sp("rsp", 16), ip("rip", 16),

        ah("rax", 8), bh("rbx", 8), ch("rcx", 8),
        dh("rdx", 8),

        al("rax", 8), bl("rbx", 8), cl("rcx", 8),
        dl("rdx", 8), bpl("rbp", 8), sil("rsi", 8),
        spl("rsp", 8), dil("rdi", 8),

        cs("cs", 16), ds("ds", 16), es("es", 16),
        fs("fs", 16), gs("gs", 16), ss("ss", 16);

        private final int size;
        private final String group;
        Reg(String group, int size) {
            this.group = group;
            this.size = size;
        }

        private String group() {return group;}
        private int size() {return size;}
    }

    public static Map<String, Register> generalRegisters = new HashMap<>();
    public static Register flagRegister = new Register(0L);
    static {
        // General Registers
        generalRegisters.put(rax.name(), new Register(0L));
        generalRegisters.put(rbx.name(), new Register(0L));
        generalRegisters.put(rcx.name(), new Register(0L));
        generalRegisters.put(rdx.name(), new Register(0L));

        generalRegisters.put(rbp.name(), new Register(0L));
        generalRegisters.put(rsi.name(), new Register(0L));
        generalRegisters.put(rdi.name(), new Register(0L));
        generalRegisters.put(rsp.name(), new Register(0L));
        generalRegisters.put(rip.name(), new Register(0L));

        generalRegisters.put(cs.name(), new Register(0L));
        generalRegisters.put(ds.name(), new Register(0L));
        generalRegisters.put(es.name(), new Register(0L));
        generalRegisters.put(fs.name(), new Register(0L));
        generalRegisters.put(gs.name(), new Register(0L));
        generalRegisters.put(ss.name(), new Register(0L));
    }

    public static String getFormattedValue(Reg register) {
        return register.name() + " : " + Long.toBinaryString(getRegisterValue(register));
    }

    public static long getRegisterValue(Reg register) {
        switch (register.size()) {
            case 64:
                return generalRegisters.get(register.group()).get64Bit();
            case 32:
                return generalRegisters.get(register.group()).get32Bit();
            case 16:
                switch (register) {
                    case ax:
                    case bx:
                    case cx:
                    case dx:
                    case bp:
                    case si:
                    case di:
                    case sp:
                    case ip:
                    case cs:
                    case ds:
                    case es:
                    case fs:
                    case gs:
                    case ss:
                        return generalRegisters.get(register.group()).get16Bit();
                }
            case 8:
                switch (register) {
                    case ah:
                    case bh:
                    case ch:
                    case dh:
                        return generalRegisters.get(register.group()).get8BitHigher();
                    case al:
                    case bl:
                    case cl:
                    case dl:
                    case bpl:
                    case sil:
                    case spl:
                    case dil:
                        return generalRegisters.get(register.group()).get8BitLower();
                }
            default:
                return 0;
        }
    }
}
