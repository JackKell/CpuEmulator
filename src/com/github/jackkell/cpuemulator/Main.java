package com.github.jackkell.cpuemulator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int source = 0b11111101;

        System.out.println(Integer.toBinaryString(source));
        System.out.println(Integer.toBinaryString(source & (1<<3)-1));
        getBits(source, 3, 0);
    }


    private static void getBits(int source, int bits, int bitoffset) {
        System.out.println(Integer.toBinaryString((source >> bitoffset) & (1 << bits) - 1));
    }

}
