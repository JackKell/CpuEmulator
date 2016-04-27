package com.github.jackkell.cpuemulator.cpu;

import com.github.jackkell.cpuemulator.util.*;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.jackkell.cpuemulator.cpu.RegisterBank.*;
import static com.github.jackkell.cpuemulator.cpu.Registers.*;

public final class ControlUnit {

    private static String generalRegisterRegex = "[er]?([a-d])[hlx]";
    private static String indexRegisterRegex = "[re]?([sd]i)l?";
    private static String pointerRegisterRegex = "[re]?([bsi]p)l?";
    private static String segmentRegisterRegex = "[cdefgs]s";


    public static void runCommand(Command command) throws Exception {
        switch (command.getOperation()) {
            case "dump":
                dump();
                break;
            case "mov":
                mov(command.getArguments());
                break;
            case "push":
                break;
            case "pop":
                break;
            case "add":
                Alu.add(command.getArguments());
                break;
            case "sub":
                Alu.sub(command.getArguments());
                break;
            case "lea":
                break;
            default:
                break;
        }
    }

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
    }

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

    private static void mov(RegisterArg destination, ConstantArg source) throws Exception {
        setRegisterValue(destination.getRegister(), source.getValue());
    }

    private static void mov(RegisterArg destination, MemoryArg source) throws Exception {
        setRegisterValue(destination.getRegister(), source.getValue());
    }

    private static void mov(RegisterArg destination, RegisterArg source) throws Exception {
        long value = getRegisterValue(source.getRegister());
        setRegisterValue(destination.getRegister(), value);
    }

    private static void mov(MemoryArg destination, ConstantArg source) {
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), source.getValue()));
    }

    private static void mov(MemoryArg destination, RegisterArg source) throws Exception {
        long value = getRegisterValue(source.getRegister());
        Memory.memory.put(destination.getName(), new MemoryValue(source.getSize(), value));
    }

    public static void push(List<String> arguments) {

    }

    public static void push(String destination, String source) {

    }

    public static void pop(List<String> arguments) {

    }

    public static void pop(String destination, String source) {

    }

    public static void lea(List<String> arguments) {

    }

    public static void lea(String destination, String source) {

    }

    public static void setRegister(String registerName, long value) throws Exception {
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(generalRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            String matchGroup = matcher.group(1);
            Register currentRegister = generalRegisters.get("r" + matchGroup + "x");
            if (Objects.equals(registerName, "r" + matchGroup + "x"))
                currentRegister.set64Bit(value);
            else if (Objects.equals(registerName, "e" + matchGroup + "x"))
                currentRegister.set32Bit((int)value);
            else if (Objects.equals(registerName, matchGroup + "x"))
                currentRegister.set16Bit((short)value);
            else if (Objects.equals(registerName, matchGroup + "l"))
                currentRegister.set8BitLower((byte)value);
            else if (Objects.equals(registerName, matchGroup + "h"))
                currentRegister.set8BitHigher((byte)value);
            return;
        }

        pattern = Pattern.compile(indexRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            String matchGroup = matcher.group(1);
            Register currentRegister = generalRegisters.get("r" + matchGroup);
            if (Objects.equals(registerName, "r" + matchGroup))
                currentRegister.set64Bit(value);
            else if (Objects.equals(registerName, "e" + matchGroup))
                currentRegister.set32Bit((int) value);
            else if (Objects.equals(registerName, matchGroup))
                currentRegister.set16Bit((short) value);
            else if (Objects.equals(registerName, matchGroup + "l"))
                currentRegister.set8BitLower((byte) value);
            return;
        }

        pattern = Pattern.compile(pointerRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            String matchGroup = matcher.group(1);
            Register currentRegister = generalRegisters.get("r" + matchGroup);
            if (Objects.equals(registerName, "r" + matchGroup))
                currentRegister.set64Bit(value);
            else if (Objects.equals(registerName, "e" + matchGroup))
                currentRegister.set32Bit((int) value);
            else if (Objects.equals(registerName, matchGroup))
                currentRegister.set16Bit((short) value);
                //else if (registerName == matchGroup + "l" && matchGroup != "ip")
            else if (!Objects.equals(Objects.equals(registerName, matchGroup + "l") + matchGroup, "ip"))
                currentRegister.set8BitLower((byte) value);
            return;
        }

        pattern = Pattern.compile(segmentRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            generalRegisters.get(registerName).set16Bit((short) value);
            return;
        }

        throw new Exception();
    }

    public static long getRegister(String registerName) throws Exception {
        registerName = registerName.toLowerCase();
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(generalRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            String matchGroup = matcher.group(1);
            Register currentRegister = generalRegisters.get("r" + matchGroup + "x");
            if (Objects.equals(registerName, "r" + matchGroup + "x"))
                return currentRegister.get64Bit();
            else if (Objects.equals(registerName, "e" + matchGroup + "x"))
                return currentRegister.get32Bit();
            else if (Objects.equals(registerName, matchGroup + "x"))
                return currentRegister.get16Bit();
            else if (Objects.equals(registerName, matchGroup + "l"))
                return currentRegister.get8BitLower();
            else if (Objects.equals(registerName, matchGroup + "h"))
                return currentRegister.get8BitHigher();
        }

        pattern = Pattern.compile(indexRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            String matchGroup = matcher.group(1);
            Register currentRegister = generalRegisters.get("r" + matchGroup);
            if (Objects.equals(registerName, "r" + matchGroup))
                return currentRegister.get64Bit();
            else if (Objects.equals(registerName, "e" + matchGroup))
                return currentRegister.get32Bit();
            else if (Objects.equals(registerName, matchGroup))
                return currentRegister.get16Bit();
            else if (Objects.equals(registerName, matchGroup + "l"))
                return currentRegister.get8BitLower();
        }

        pattern = Pattern.compile(pointerRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            String matchGroup = matcher.group(1);
            Register currentRegister = generalRegisters.get("r" + matchGroup);
            if (Objects.equals(registerName, "r" + matchGroup))
                return currentRegister.get64Bit();
            else if (Objects.equals(registerName, "e" + matchGroup))
                return currentRegister.get32Bit();
            else if (Objects.equals(registerName, matchGroup))
                return currentRegister.get16Bit();
            //else if (registerName == matchGroup + "l" && matchGroup != "ip")
            else if (!Objects.equals(Objects.equals(registerName, matchGroup + "l") + matchGroup, "ip"))
                return currentRegister.get8BitLower();
        }

        pattern = Pattern.compile(segmentRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            return generalRegisters.get(registerName).get16Bit();
        }
        else {
            throw new Exception();
        }
    }
}
