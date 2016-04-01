package com.github.jackkell.cpuemulator;

import com.github.jackkell.cpuemulator.cpu.Register;

public class Main {
    public static void main(String[] args) {
        Register register = new Register(5L);
        System.out.println(Long.toBinaryString(register.get8BitLower()));
        //System.out.println(register.getBit(0));
        //System.out.println(register.getBit(1));
        //System.out.println(register.getBit(2));
        System.out.println(Integer.toBinaryString(5 >> 2 & 1));
    }
}
