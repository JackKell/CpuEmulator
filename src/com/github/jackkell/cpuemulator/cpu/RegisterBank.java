package com.github.jackkell.cpuemulator.cpu;

import com.github.jackkell.cpuemulator.util.Register;
import com.github.jackkell.cpuemulator.util.Registers;

import java.util.HashMap;
import java.util.Map;

import static com.github.jackkell.cpuemulator.util.Registers.*;

/*
The Register Bank represents where all of the registers are located on the CPU. The register bank is a able to
get and set the register within the bank.
 */
public final class RegisterBank {
    // The general purpose registers
    public static Map<String, Register> generalRegisters = new HashMap<>();

    // The flag status register
    public static Register flagRegister = new Register(0L);

    // When the final class Register Bank is created it starts with the following initialization
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

    // Returns a formatted string for a given register value
    public static String getFormattedValue(Registers register) {
        return register.name() + " : " + Long.toBinaryString(getRegisterValue(register));
    }

    // Get the value of a segment of a register
    public static long getRegisterValue(Registers register) {
        switch (register.size()) {
            case 64:
                return generalRegisters.get(register.group()).get64Bit();
            case 32:
                return generalRegisters.get(register.group()).get32Bit();
            case 16:
                return generalRegisters.get(register.group()).get16Bit();
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

    // Set the value of a given register based on a given value
    public static void setRegisterValue(Registers register, long value) {
        switch (register.size()) {
            case 64:
                generalRegisters.get(register.group()).set64Bit(value);
                return;
            case 32:
                generalRegisters.get(register.group()).set32Bit((int) value);
                return;
            case 16:
                generalRegisters.get(register.group()).set16Bit((short) value);
                return;
            case 8:
                switch (register) {
                    case ah:
                    case bh:
                    case ch:
                    case dh:
                        generalRegisters.get(register.group()).set8BitHigher((byte) value);
                        return;
                    case al:
                    case bl:
                    case cl:
                    case dl:
                    case bpl:
                    case sil:
                    case spl:
                    case dil:
                        generalRegisters.get(register.group()).set8BitLower((byte) value);
                }
        }
    }
}
