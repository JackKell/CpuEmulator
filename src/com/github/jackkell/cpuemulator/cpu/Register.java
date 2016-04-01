package com.github.jackkell.cpuemulator.cpu;


public class Register {

    public Register(Long value) {
        this.value = value;
    }

    private Long value;

    public void setBit(int index, int bitValue) {
        if (bitValue != 0) {
            value = value | 1 << index;
        } else {
            value = value & ~(1 << index);
        }
    }

    public void setBits(int newBits, int bitLength, int offset) {
        int valueLen = Long.toBinaryString(value).length();
        long savedSourceOffset = 0;
        if (offset > 0) {
            savedSourceOffset = Long.parseLong(Long.toBinaryString(value).substring(valueLen - offset, valueLen));
        }
        value = (value >> bitLength + offset << bitLength + offset) | (newBits << offset) | savedSourceOffset;
    }

    private long getBits(int numberOfBits, int bitOffset) {
        return (value >> bitOffset) & ((1 << numberOfBits) - 1);
    }

    private long getBits(int numberOfBits) {
        return getBits(numberOfBits, 0);
    }

    public long get64Bit() {
        return value;
    }

    public void set64Bit(long input) {
        this.value = input;
    }

    public long get32Bit() {
        return getBits(32);
    }

    public void set32Bit(int input) {
        value = value >> 32 << 32 | input;
    }

    public long get16Bit() {
        return getBits(16);
    }

    public void set16Bit(short input) {
        value = value >> 16 << 16 | input;
    }

    public long get8BitLower() {
        return getBits(8);
    }

    public void set8BitLower(Byte input) {
        value = value >> 8 << 8 | input;
    }

    public long get8BitHigher() {
        return getBits(8, 8);
    }

    public void set8BitHigher(Byte input) {
        value = (((value >> 16 << 8) | input) << 8) | get8BitLower();
    }
}

/*

1010 |
0100 = 1<<2
1110

1110 &
1011 = ~(1<<2)
1010

1011 |
1101 = 1001 | (0b10<<1)
*/



