package com.github.jackkell.cpuemulator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterTest {
    @Test
    public void getBits() {
        Register register = new Register(0x1234L);
        assertEquals("The getbits(bitmask) does not return the correct value", 0x4, register.getBits(0xF));
        assertEquals("The getbits(bitmask, bitoffset) does not return the correct value", 0x3, register.getBits(0xF, 0x4));
    }

    @Test
    public void get64Bit() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get64Bit() does not return correct value", 0x79B27E5B8F7D29C2L, register.get64Bit());
    }

    @Test
    public void get32Bit() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get32Bit() does not return correct value", 0x8F7D29C2, register.get32Bit());
    }

    @Test
    public void get16Bit() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get16Bit() does not return correct value", 0x29C2, register.get16Bit());
    }

    @Test
    public void get8BitLower() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get16Bit() does not return correct value", 0xC2, register.get16Bit());
    }

    @Test
    public void get8BitHigher() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get16Bit() does not return correct value", 0x29, register.get16Bit());
    }
}