package com.github.jackkell.cpuemulator.cpu;

public final class Alu {
    public static long Add(long value1, long value2) {
        return value1 + value2;
    }

    public static long Subtract(long value1, long value2) {
        return value1 - value2;
    }

    public static long Multiply(long value1, long value2) {
        return value1 * value2;
    }

    public static long Divide(long value1, long value2) {
        return value1 / value2;
    }
}
