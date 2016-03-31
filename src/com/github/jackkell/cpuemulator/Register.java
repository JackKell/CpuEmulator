package com.github.jackkell.cpuemulator;

public class Register {

    public Register(Long value) {
        this.value = value;
    }

    private Long value;

    public long getBits(int bitmask, int bitoffset) {
        return (value >> bitoffset) & bitmask;
    }

    public long getBits(int bitmask) {
        return getBits(bitmask, 0);
    }

    public long get64Bit() {
        return value;
    }

    public long get32Bit() {
        return getBits(0xFFFFFF);
    }

    public long get16Bit() {
        return getBits(0xFFFF);
    }

    public long get8BitLower() {
        return getBits(0xFF);
    }

    public long get8BitHigher() {
        return getBits(0xFF, 8);
    }
}
