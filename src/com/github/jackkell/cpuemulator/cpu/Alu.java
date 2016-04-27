package com.github.jackkell.cpuemulator.cpu;

import com.github.jackkell.cpuemulator.util.CommandArg;
import com.github.jackkell.cpuemulator.util.ConstantArg;
import com.github.jackkell.cpuemulator.util.MemoryArg;
import com.github.jackkell.cpuemulator.util.RegisterArg;

import java.util.List;

import static com.github.jackkell.cpuemulator.cpu.RegisterBank.*;

@SuppressWarnings("Duplicates")
public final class Alu {

    private static void updateFlags(int size, long value1, long value2, long result) {
        updateFlags(size, value1, value2, result, false);
    }

    private static void updateFlags(int size, long value1, long value2, long result , boolean isAddSub) {
        String binaryValue1 = new StringBuilder().append(String.format("%64s", Long.toBinaryString(value1)).replace(' ', '0')).reverse().toString();
        String binaryValue2 = new StringBuilder().append(String.format("%64s", Long.toBinaryString(value2)).replace(' ', '0')).reverse().toString();
        String binaryResult = new StringBuilder().append(String.format("%64s", Long.toBinaryString(result)).replace(' ', '0')).reverse().toString();
        // Set the carry flag
        if (isAddSub) {
            if ((binaryValue1.charAt(size - 1) != binaryResult.charAt(size - 1)) && (binaryValue1.charAt(size - 1) == binaryValue2.charAt(size - 1))) {
                RegisterBank.flagRegister.setBit(0, 1);
            } else {
                RegisterBank.flagRegister.setBit(0, 0);
            }
        }
        int setBitCount = 0;
        for (char bit : binaryResult.toCharArray()) {
            setBitCount++;
        }

        // Set the parity flag
        if (setBitCount % 2 == 0) {
            RegisterBank.flagRegister.setBit(2, 1);
        } else {
            RegisterBank.flagRegister.setBit(2, 0);
        }

        // Set the adjust flag
        if (isAddSub) {
            if ((binaryValue1.charAt(3) != binaryResult.charAt(3)) && (binaryValue1.charAt(3) == binaryValue2.charAt(3))) {
                RegisterBank.flagRegister.setBit(4, 1);
            } else {
                RegisterBank.flagRegister.setBit(4, 0);
            }
        }

        // Set the zero flag
        if (result == 0) {
            RegisterBank.flagRegister.setBit(6, 1);
        } else {
            RegisterBank.flagRegister.setBit(6, 0);
        }

        // Set the sign flag
        if (binaryResult.charAt(size - 1) == '1') {
            RegisterBank.flagRegister.setBit(7, 1);
        } else {
            RegisterBank.flagRegister.setBit(7, 0);
        }

        // Set the sign flag
        if (binaryResult.charAt(size - 1) == '1') {
            RegisterBank.flagRegister.setBit(7, 1);
        } else {
            RegisterBank.flagRegister.setBit(7, 0);
        }
    }

    private static void checkSize(int destinationSize, int sourceSize) throws Exception {
        if (destinationSize < sourceSize) {
            throw new Exception("The source is of a greater size than the destination.");
        }
    }

    public static void add(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 2) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                add((RegisterArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class) {
                add((RegisterArg) arg1, (MemoryArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class) {
                add((RegisterArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arg2.getClass() == ConstantArg.class) {
                add((MemoryArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arguments.get(1).getClass() == RegisterArg.class) {
                add((MemoryArg) arg1, (RegisterArg) arg2);
            } else {
                throw new Exception("Arguments used are not supported by the add function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 2 for the add function.");
        }
    }

    private static void add(RegisterArg destination, ConstantArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 + value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void add(RegisterArg destination, MemoryArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 + value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void add(RegisterArg destination, RegisterArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 + value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void add(MemoryArg destination, ConstantArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = destination.getValue();
        long value2 = source.getValue();
        long result = value1 + value2;
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void add(MemoryArg destination, RegisterArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = destination.getValue();
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 + value2;
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    public static void sub(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 2) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                sub((RegisterArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class) {
                sub((RegisterArg) arg1, (MemoryArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class) {
                sub((RegisterArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arg2.getClass() == ConstantArg.class) {
                sub((MemoryArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arguments.get(1).getClass() == RegisterArg.class) {
                sub((MemoryArg) arg1, (RegisterArg) arg2);
            } else {
                throw new Exception("Arguments used are not supported by the sub function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 2 for the add function.");
        }
    }

    private static void sub(RegisterArg destination, ConstantArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 - value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void sub(RegisterArg destination, MemoryArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 - value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void sub(RegisterArg destination, RegisterArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 - value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void sub(MemoryArg destination, ConstantArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = destination.getValue();
        long value2 = source.getValue();
        long result = value1 - value2;
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void sub(MemoryArg destination, RegisterArg source) throws Exception {
        checkSize(destination.getSize(), source.getSize());
        long value1 = destination.getValue();
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 - value2;
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    public static void inc(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 1) {
            CommandArg arg1 = arguments.get(0);
            ConstantArg constantArg = new ConstantArg(arg1.getSize(), 1);
            if (arg1.getClass() == RegisterArg.class) {
                add((RegisterArg) arg1, constantArg);
            } else if (arg1.getClass() == MemoryArg.class) {
                add((MemoryArg) arg1, constantArg);
            } else {
                throw new Exception("Argument is not supported by the inc function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 1 for the inc function.");
        }
    }

    public static void dec(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 1) {
            CommandArg arg1 = arguments.get(0);
            ConstantArg constantArg = new ConstantArg(arg1.getSize(), 1);
            if (arg1.getClass() == RegisterArg.class) {
                sub((RegisterArg) arg1, constantArg);
            } else if (arg1.getClass() == MemoryArg.class) {
                sub((MemoryArg) arg1, constantArg);
            } else {
                throw new Exception("Argument is not supported by the dec function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 1 for the dec function.");
        }
    }

    public static void imul(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 2) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class) {
                imul((RegisterArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class) {
                imul((RegisterArg) arg1, (MemoryArg) arg2);
            } else {
                throw new Exception("Arguments used are not supported by the imul function.");
            }
        } else if (arguments.size() == 3) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            CommandArg arg3 = arguments.get(2);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class && arg3.getClass() == ConstantArg.class) {
                imul((RegisterArg) arg1, (RegisterArg) arg2, (ConstantArg) arg3);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class && arg3.getClass() == ConstantArg.class) {
                imul((RegisterArg) arg1, (MemoryArg) arg2, (ConstantArg) arg3);
            } else {
                throw new Exception("Arguments used are not supported by the imul function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 2 or 3 for the add function.");
        }
    }

    private static void imul(RegisterArg destination, RegisterArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 * value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);

    }

    private static void imul(RegisterArg destination, MemoryArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 * value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);

    }

    private static void imul(RegisterArg destination, RegisterArg source1, ConstantArg source2) {
        long value1 = getRegisterValue(source1.getRegister());
        long value2 = source2.getValue();
        long result = value1 * value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void imul(RegisterArg destination, MemoryArg source1, ConstantArg source2) {
        long value1 = source1.getValue();
        long value2 = source2.getValue();
        long result = value1 * value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    public static void idiv(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 1) {
            CommandArg arg1 = arguments.get(0);
            if (arg1.getClass() == RegisterArg.class) {
                idiv((RegisterArg) arg1);
            } else if (arg1.getClass() == MemoryArg.class) {
                idiv((MemoryArg) arg1);
            } else {
                throw new Exception("Arguments used are not supported by the imul function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 1 for the add function.");
        }
    }

    private static void idiv(RegisterArg source) {
        long value1 = getRegisterValue(Registers.rax);
        long value2 = getRegisterValue(source.getRegister());
        long result1 = value1 / value2;
        long result2 = value1 % value2;
        setRegisterValue(Registers.rax, result1);
        setRegisterValue(Registers.rdx, result2);
    }

    private static void idiv(MemoryArg source) {
        long value1 = getRegisterValue(Registers.rax);
        long value2 = source.getValue();
        long result1 = value1 / value2;
        long result2 = value1 % value2;
        setRegisterValue(Registers.rax, result1);
        setRegisterValue(Registers.rdx, result2);
    }

    public static void and(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 2) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class) {
                and((RegisterArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class) {
                and((RegisterArg) arg1, (MemoryArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arg2.getClass() == RegisterArg.class) {
                and((MemoryArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                and((RegisterArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                and((MemoryArg) arg1, (ConstantArg) arg2);
            } else {
                throw new Exception("Arguments used are not supported by the and function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 2 for the and function.");
        }
    }

    private static void and(RegisterArg destination, RegisterArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 & value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void and(RegisterArg destination, MemoryArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 & value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void and(MemoryArg destination, RegisterArg source) {
        long value1 = destination.getValue();
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 & value2;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void and(RegisterArg destination, ConstantArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 & value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void and(MemoryArg destination, ConstantArg source) {
        long value1 = destination.getValue();
        long value2 = source.getValue();
        long result = value1 & value2;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    public static void or(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 2) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class) {
                or((RegisterArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class) {
                or((RegisterArg) arg1, (MemoryArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arg2.getClass() == RegisterArg.class) {
                or((MemoryArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                or((RegisterArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                or((MemoryArg) arg1, (ConstantArg) arg2);
            } else {
                throw new Exception("Arguments used are not supported by the and function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 2 for the and function.");
        }
    }

    private static void or(RegisterArg destination, RegisterArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 & value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void or(RegisterArg destination, MemoryArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 | value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void or(MemoryArg destination, RegisterArg source) {
        long value1 = destination.getValue();
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 | value2;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void or(RegisterArg destination, ConstantArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 | value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void or(MemoryArg destination, ConstantArg source) {
        long value1 = destination.getValue();
        long value2 = source.getValue();
        long result = value1 | value2;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    public static void xor(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 2) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class) {
                xor((RegisterArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class) {
                xor((RegisterArg) arg1, (MemoryArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arg2.getClass() == RegisterArg.class) {
                xor((MemoryArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                xor((RegisterArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                xor((MemoryArg) arg1, (ConstantArg) arg2);
            } else {
                throw new Exception("Arguments used are not supported by the and function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 2 for the and function.");
        }
    }

    private static void xor(RegisterArg destination, RegisterArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 ^ value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void xor(RegisterArg destination, MemoryArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 ^ value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void xor(MemoryArg destination, RegisterArg source) {
        long value1 = destination.getValue();
        long value2 = getRegisterValue(source.getRegister());
        long result = value1 ^ value2;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void xor(RegisterArg destination, ConstantArg source) {
        long value1 = getRegisterValue(destination.getRegister());
        long value2 = source.getValue();
        long result = value1 ^ value2;
        setRegisterValue(destination.getRegister(), result);
        updateFlags(destination.getSize(), value1, value2, result);
    }

    private static void xor(MemoryArg destination, ConstantArg source) {
        long value1 = destination.getValue();
        long value2 = source.getValue();
        long result = value1 ^ value2;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
        updateFlags(destination.getSize(), value1, value2, result);
    }

    public static void not(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 1) {
            CommandArg arg1 = arguments.get(0);
            if (arg1.getClass() == RegisterArg.class) {
                not((RegisterArg) arg1);
            } else if (arg1.getClass() == MemoryArg.class) {
                not((MemoryArg) arg1);
            } else {
                throw new Exception("Arguments used are not supported by the not function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 1 for the not function.");
        }
    }

    private static void not(RegisterArg destination) {
        long value = getRegisterValue(destination.getRegister());
        long result = ~value;
        setRegisterValue(destination.getRegister(), result);
    }

    private static void not(MemoryArg destination) {
        long value = destination.getValue();
        long result = ~value;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
    }

    public static void neg(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 1) {
            CommandArg arg1 = arguments.get(0);
            if (arg1.getClass() == RegisterArg.class) {
                neg((RegisterArg) arg1);
            } else if (arg1.getClass() == MemoryArg.class) {
                neg((MemoryArg) arg1);
            } else {
                throw new Exception("Arguments used are not supported by the not function.");
            }
        } else {
            throw new Exception("The number of arguments must be equal to 1 for the not function.");
        }
    }

    private static void neg(RegisterArg destination) {
        long value = getRegisterValue(destination.getRegister());
        long result = ~value + 1;
        setRegisterValue(destination.getRegister(), result);
    }

    private static void neg(MemoryArg destination) {
        long value = destination.getValue();
        long result = ~value + 1;
        Memory.memory.put(destination.getName(), new MemoryValue(destination.getSize(), result));
    }


    // TODO: finish shift left and shift right
    public static void shl(List<CommandArg> arguments) {
        System.out.println("Shift left command not yet supported by C.A.x.E");
    }

    public static void shr(List<CommandArg> arguments) {
        System.out.println("Shift right command not yet supported by C.A.x.E");
    }
}
