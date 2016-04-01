package com.github.jackkell.cpuemulator.cpu;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ControlUnit {

    private static String generalRegisterRegex = "[er]?([a-d])[hlx]";
    private static String indexRegisterRegex = "[re]?([sd]i)l?";
    private static String pointerRegisterRegex = "[re]?([bsi]p)l?";
    private static String segmentRegisterRegex = "[cdefgs]s";


    public static void runCommand(Command command) {
        switch (command.getOperation()) {
            case "mov":
                mov(command.getArguments());
                break;
            case "push":
                break;
            case "pop":
                break;
            case "lea":
                break;
            default:
                break;
        }
    }

    public static void mov(List<String> arguments) {

    }

    public static void mov(String destination, String source) {

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

    public static void add(List<String> arguments) {

    }

    public static void add(String destination, String source) {

    }

    public static void sub(List<String> arguments) {

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

    public static long getRegister(String registerName) throws Exception {
        registerName = registerName.toLowerCase();
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(generalRegisterRegex);
        matcher = pattern.matcher(registerName);
        if (matcher.matches()) {
            String matchGroup = matcher.group(1);
            Register currentRegister = RegisterBank.generalRegisters.get("r" + matchGroup + "x");
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
            Register currentRegister = RegisterBank.generalRegisters.get("r" + matchGroup);
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
            Register currentRegister = RegisterBank.generalRegisters.get("r" + matchGroup);
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
            return RegisterBank.segmentRegisters.get(registerName).get16Bit();
        }
        else {
            throw new Exception();
        }
    }
}
