package com.github.jackkell.cpuemulator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //checks if something is a valid command
        // "^(...) +((?:[EeRr]|)[A-Da-dSs][XxHhLlPp]), +((?:\\d+|(?:[EeRr]|)[A-Da-dSs][XxHhLlPp]))$"
        String registerName = "xxx";
        Number value = null;
        try {
            value = ControlUnit.getRegister(registerName);
        } catch (Exception e) {
            System.out.println("Invalid Register");
        }
        System.out.print(registerName + " : ");
        System.out.println(value);
    }
}
