package com.github.jackkell.cpuemulator;

public class Register {

    public Register(Long value) {
        this.value = value;
    }

    private Long value;

    public long get64Bit() {
        return value;
    }

    public int get32Bit() {
        return value.intValue();
    }

    public short get16Bit() {
        return value.shortValue();
    }

    public byte get8BitLower() {
        return (byte)(value.intValue() & 0xFF);
    }

    public byte get8BitHigher() {
        return (byte)((value.intValue() >> 8) & 0xFF);
    }
}
