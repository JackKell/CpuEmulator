package com.github.jackkell.cpuemulator.cpu;

import com.github.jackkell.cpuemulator.util.CommandArg;
import com.github.jackkell.cpuemulator.util.ConstantArg;
import com.github.jackkell.cpuemulator.util.MemoryArg;
import com.github.jackkell.cpuemulator.util.RegisterArg;

import java.util.List;

public final class Alu {

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
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    private static void add(RegisterArg destination, ConstantArg source) {

    }

    private static void add(RegisterArg destination, MemoryArg source) {

    }

    private static void add(RegisterArg destination, RegisterArg source) {

    }

    private static void add(MemoryArg destination, ConstantArg source) {

    }

    private static void add(MemoryArg destination, RegisterArg source) {

    }

    public static void sub(List<CommandArg> arguments) throws Exception {
        //long result = getRegister(arguments.get(0)) - Long.parseLong(arguments.get(1));
        //setRegister(arguments.get(0), result);
    }

    public static void sub(String destination, String source) {

    }

    public static void inc(List<String> arguments) {

    }

    public static void inc(String destination) {

    }

    public static void dec(List<String> arguments) {

    }

    public static void dec(String destination) {

    }

    public static void imul(List<String> arguments) {

    }

    public static void imul(String destination) {

    }

    public static void idiv(List<String> arguments) {

    }

    public static void idiv(String destination) {

    }

    public static void and(List<String> arguments) {

    }

    public static void and(String destination, String source) {

    }

    public static void or(List<String> arguments) {

    }

    public static void or(String destination, String source) {

    }

    public static void xor(List<String> arguments) {

    }

    public static void xor(String destination, String source) {

    }

    public static void not(List<String> arguments) {

    }

    public static void not(String destination, String source) {

    }

    public static void neg(List<String> arguments) {

    }

    public static void neg(String destination, String source) {

    }

    public static void shl(List<String> arguments) {

    }

    public static void shl(String destination, String source) {

    }

    public static void shr(List<String> arguments) {

    }

    public static void shr(String destination, String source) {

    }

    public static void cmp(List<String> arguments) {

    }

    public static void cmp(String destination, String source) {

    }

    public static void updateFlags(long value1, long value2, long result, int size) {

    }
}
