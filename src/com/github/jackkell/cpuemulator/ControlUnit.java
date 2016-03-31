package com.github.jackkell.cpuemulator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ControlUnit {

    private static String generalRegisterRegex = "[er]?([a-d])[hlx]";
    private static String indexRegisterRegex = "[re]?([sd]i)l?";
    private static String pointerRegisterRegex = "[re]?([bsi]p)l?";
    private static String segmentRegisterRegex = "[cdefgs]s";
    private static String flagRegisterRegex = "[cpazstido]f";

    public static void Mov(String destination, String source) {

    }

    public static void Push(String destination, String source) {

    }

    public static void Pop(String destination, String source) {

    }

    public static void Lea(String destination, String source) {

    }

    public static void Add(String destination, String source) {

    }

    public static void Sub(String destination, String source) {

    }

    public static void Inc(String destination) {

    }

    public static void Dec(String destination) {

    }

    // TODO: Fix the Imul and the Idiv functions
    public static void Imul(String destination) {

    }

    public static void Idiv(String destination) {

    }

    public static void And(String destination, String source) {

    }

    public static void Or(String destination, String source) {

    }

    public static void Xor(String destination, String source) {

    }

    public static void Not(String destination, String source) {

    }

    public static void Neg(String destination, String source) {

    }

    public static void Shl(String destination, String source) {

    }

    public static void Shr(String destination, String source) {

    }

    public static void Cmp(String destination, String source) {

    }

    public static Number getRegister(String registerName) throws Exception {
        registerName = registerName.toLowerCase();
        Pattern pattern;
        Matcher matcher;
        if (registerName.matches(generalRegisterRegex)) {
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
        }
        else if (registerName.matches(indexRegisterRegex)) {
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
        }

        else if (registerName.matches(pointerRegisterRegex)) {
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
        }
        else if (registerName.matches(segmentRegisterRegex)) {
            pattern = Pattern.compile(segmentRegisterRegex);
            matcher = pattern.matcher(registerName);
            if (matcher.matches()) {
                return RegisterBank.segmentRegisters.get(registerName).get16Bit();
            }
        }
        else if (registerName.matches(flagRegisterRegex)) {
            pattern = Pattern.compile(flagRegisterRegex);
            matcher = pattern.matcher(registerName);
            if (matcher.matches()) {
                return RegisterBank.flagRegisters.get(registerName).get16Bit();
            }
        }
        else {
            throw new Exception();
        }
        return 0;
    }
}
