package com.github.jackkell.cpuemulator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //checks if something is a valid command
        // "^(...) +((?:[EeRr]|)[A-Da-dSs][XxHhLlPp]), +((?:\\d+|(?:[EeRr]|)[A-Da-dSs][XxHhLlPp]))$"
        int value = 0x54321;
        System.out.println((value >> 0x4) & 0xF);
    }
}
