package com.github.jackkell.cpuemulator.util;


public class Register {

    public Register(Long value) {
        this.value = value;
    }

    private long value;

    public long getBit(int index) {
        return value >> index & 1;
    }

    private long getBits(int numberOfBits, int bitOffset) {
        return (value >> bitOffset) & ((1L << numberOfBits) - 1);
    }

    private long getBits(int numberOfBits) {
        return getBits(numberOfBits, 0);
    }

    public long get64Bit() {
        return value;
    }

    public long get32Bit() {
        return getBits(32);
    }

    public long get16Bit() {
        return getBits(16);
    }

    public long get8BitLower() {
        return getBits(8);
    }

    public long get8BitHigher() {
        return getBits(8, 8);
    }

    public void setBit(int index, int bitValue) {
        if (bitValue != 0) {
            value = value | 1 << index;
        } else {
            value = value & ~(1 << index);
        }
    }

    public void set64Bit(long input) {
        this.value = input;
    }

    public void set32Bit(int input) {
        value = value >> 32 << 32 | (input & 0xFFFFFFFFL);
    }

    public void set16Bit(short input) {
        value = value >> 16 << 16 | (input & 0xFFFF);
    }

    public void set8BitLower(Byte input) {
        value = value >> 8 << 8 | (input & 0xFF);
    }

    public void set8BitHigher(Byte input) {
        value = (((value >> 16 << 8) | (input & 0xFF)) << 8) | get8BitLower();
    }
}



