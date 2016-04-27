package com.github.jackkell.cpuemulator.cpu;

import com.github.jackkell.cpuemulator.util.*;

import java.util.List;

import static com.github.jackkell.cpuemulator.cpu.Alu.*;
import static com.github.jackkell.cpuemulator.cpu.RegisterBank.*;
import static com.github.jackkell.cpuemulator.util.Registers.*;

/*
The Control Unit decides which function are run base on the given command. Additionally
the control unit handles functions that control the flow of the program.
 */
public final class ControlUnit {
    // The function determines which function will be run
    public static void runCommand(Command command) throws Exception {
        List<CommandArg> arguments = command.getArguments();
        Operation operation = command.getOperation();
        switch (operation) {
            case dump:
                dump();
                break;
            case mov:
                mov(arguments);
                break;
            case push:
                push(arguments);
                break;
            case pop:
                pop(arguments);
                break;
            case add:
                add(arguments);
                break;
            case sub:
                sub(arguments);
                break;
            case inc:
                inc(arguments);
                break;
            case dec:
                dec(arguments);
                break;
            case imul:
                imul(arguments);
                break;
            case idiv:
                idiv(arguments);
                break;
            case and:
                add(arguments);
                break;
            case or:
                or(arguments);
                break;
            case xor:
                xor(arguments);
                break;
            case not:
                not(arguments);
                break;
            case neg:
                neg(arguments);
                break;
            case shl:
                shl(arguments);
                break;
            case shr:
                shr(arguments);
                break;
            case lea:
                lea(arguments);
                break;
            case cmp:
                cmp(arguments);
                break;
            case cld:
                cld();
                break;
            case std:
                std();
                break;
            case data:
                data(arguments);
                break;

        }
    }

    // dump an operation that prints all registers, flags, and memory to the screen
    public static void dump() throws Exception {
        System.out.println(getFormattedValue(rax));
        System.out.println(getFormattedValue(rbx));
        System.out.println(getFormattedValue(rcx));
        System.out.println(getFormattedValue(rdx));
        System.out.println();
        System.out.println(getFormattedValue(rbp));
        System.out.println(getFormattedValue(rsi));
        System.out.println(getFormattedValue(rdi));
        System.out.println(getFormattedValue(rsp));
        System.out.println(getFormattedValue(rip));
        System.out.println();
        System.out.println(getFormattedValue(rbp));
        System.out.println(getFormattedValue(rbp));
        System.out.println(getFormattedValue(rbp));
        System.out.println(getFormattedValue(rbp));
        System.out.println(getFormattedValue(rbp));
        System.out.println(getFormattedValue(rbp));
        System.out.println();
        System.out.println("Flag: " + Long.toBinaryString(flagRegister.get64Bit()));
        System.out.println();
        System.out.println("Memory");
        for (String key : Memory.memory.keySet()) {
            System.out.println(key + " : " + Long.toBinaryString(Memory.memory.get(key).value));
        }
    }

    // Assigns a value to another value
    private static void mov(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 2) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            if (arg1.getClass() == RegisterArg.class && arg2.getClass() == ConstantArg.class) {
                mov((RegisterArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == MemoryArg.class) {
                mov((RegisterArg) arg1, (MemoryArg) arg2);
            } else if (arg1.getClass() == RegisterArg.class && arg2.getClass() == RegisterArg.class) {
                mov((RegisterArg) arg1, (RegisterArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arg2.getClass() == ConstantArg.class) {
                mov((MemoryArg) arg1, (ConstantArg) arg2);
            } else if (arg1.getClass() == MemoryArg.class && arguments.get(1).getClass() == RegisterArg.class) {
                mov((MemoryArg) arg1, (RegisterArg) arg2);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    // Assigns a constant value to a register value
    private static void mov(RegisterArg destination, ConstantArg source) throws Exception {
        setRegisterValue(destination.getRegister(), source.getValue());
    }

    // Assigns a memory value to a register value
    private static void mov(RegisterArg destination, MemoryArg source) throws Exception {
        setRegisterValue(destination.getRegister(), source.getValue());
    }

    // Assigns a memory value to a register value
    private static void mov(RegisterArg destination, RegisterArg source) throws Exception {
        long value = getRegisterValue(source.getRegister());
        setRegisterValue(destination.getRegister(), value);
    }

    // Assigns a constant value to a memory value
    private static void mov(MemoryArg destination, ConstantArg source) {
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), source.getValue()));
    }

    // Assigns a register value to a memory value
    private static void mov(MemoryArg destination, RegisterArg source) throws Exception {
        long value = getRegisterValue(source.getRegister());
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), value));
    }

    // Sets the direction flag to in-active
    private static void cld() {
        RegisterBank.flagRegister.setBit(10, 0);
    }

    // Sets the direction flag to active
    private static void std() {
        RegisterBank.flagRegister.setBit(10, 1);
    }

    // A command that creates a value in memory
    private static void data(List<CommandArg> arguments) throws Exception {
        if (arguments.size() == 3) {
            CommandArg arg1 = arguments.get(0);
            CommandArg arg2 = arguments.get(1);
            CommandArg arg3 = arguments.get(2);
            if (arg1.getClass() == StringArg.class && arg2.getClass() == ConstantArg.class && arg3.getClass() == ConstantArg.class) {
                data((StringArg) arg1, (ConstantArg) arg2, (ConstantArg) arg3);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    // Creates a new value in memory with the given name, size, and value
    private static void data(StringArg name, ConstantArg size, ConstantArg value) {
        Memory.memory.put(name.getValue(), new MemoryValue(size.getSize(), value.getValue()));
    }

    // TODO: finish control flow and memory/stack functions
    public static void push(List<CommandArg> arguments) {
        System.out.println("Push command not yet supported by C.A.x.E");
    }

    public static void pop(List<CommandArg> arguments) {
        System.out.println("Pop command not yet supported by C.A.x.E");
    }

    public static void lea(List<CommandArg> arguments) {
        System.out.println("Lea command not yet supported by C.A.x.E");
    }

    public static void cmp(List<CommandArg> arguments) {
        System.out.println("Cmp command not yet supported by C.A.x.E");
    }
}
