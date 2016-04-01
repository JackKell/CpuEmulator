package com.github.jackkell.cpuemulator.test;

import com.github.jackkell.cpuemulator.cpu.Register;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterTest {
    @Test
    public void get64Bit() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get64Bit() does not return correct value", 0x79B27E5B8F7D29C2L, register.get64Bit());
        System.out.println(Long.toBinaryString(register.get64Bit()));
    }

    @Test
    public void get32Bit() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get32Bit() does not return correct value", 0x8F7D29C2L, register.get32Bit());
        System.out.println(Long.toBinaryString(register.get32Bit()));
    }

    @Test
    public void get16Bit() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get16Bit() does not return correct value", 0x29C2L, register.get16Bit());
        System.out.println(Long.toBinaryString(register.get16Bit()));
    }

    @Test
    public void get8BitLower() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get16Bit() does not return correct value", 0xC2L, register.get8BitLower());
        System.out.println(Long.toBinaryString(register.get8BitLower()));
    }

    @Test
    public void get8BitHigher() throws Exception {
        Register register = new Register(0x79B27E5B8F7D29C2L);
        assertEquals("get16Bit() does not return correct value", 0x29L, register.get8BitHigher());
        System.out.println(Long.toBinaryString(register.get8BitHigher()));
    }

    @Test
    public void getBit() throws Exception {
        Register register = new Register(5L);
        assertEquals("getBit() does not return correct value", 1, register.getBit(0));
        assertEquals("getBit() does not return correct value", 0, register.getBit(1));
        assertEquals("getBit() does not return correct value", 1, register.getBit(2));
    }

    @Test
    public void setBit() throws Exception {
        Register register = new Register(5L);
        System.out.println(register.get64Bit());
        assertEquals("The register's value at index 2 is not 1", 1, register.getBit(2));
        register.setBit(2, 0);
        System.out.println(register.get64Bit());
        assertEquals("The register's value at index 2 is not 0", 0, register.getBit(2));
        register.setBit(2, 1);
        System.out.println(register.get64Bit());
        assertEquals("The register's value at index 2 is not 1", 1, register.getBit(2));
    }

    @Test
    public void set64Bit() throws Exception {
        Register register = new Register(0L);
        System.out.println(register.get64Bit());
        assertEquals("The register's value is not 0", 0, register.get64Bit());
        register.set64Bit(2500003022L);
        System.out.println(register.get64Bit());
        assertEquals("The register's value is not 2500003022L", 2500003022L, register.get64Bit());
    }

    @Test
    public void set32Bit() throws Exception {
        Register register = new Register(0L);
        System.out.println(register.get32Bit());
        assertEquals("The register's value is not 0", 0, register.get32Bit());
        register.set32Bit(2500003);
        System.out.println(register.get32Bit());
        assertEquals("The register's value is not 2500003", 2500003, register.get32Bit());
    }

    @Test
    public void set16Bit() throws Exception {
        Register register = new Register(0L);
        System.out.println(register.get16Bit());
        assertEquals("The register's value is not 0", 0, register.get16Bit());
        register.set16Bit((short)1000);
        System.out.println(register.get16Bit());
        assertEquals("The register's value is not 1000", 1000, register.get16Bit());
        short x = (byte)500000000;
        System.out.println(x);
    }

    @Test
    public void set8BitHigher() throws Exception {
        Register register = new Register(0L);
        System.out.println(register.get8BitLower());
        assertEquals("The register's value is not 0", 0, register.get8BitLower());
        register.set8BitLower((byte) 200);
        System.out.println(register.get8BitLower());
        assertEquals("The register's value is not 200", 200, register.get8BitLower());
    }

    @Test
    public void set8BitLower() throws Exception {
        Register register = new Register(0L);
        System.out.println(register.get8BitHigher());
        assertEquals("The register's value is not 0", 0, register.get8BitHigher());
        register.set8BitHigher((byte) 200);
        System.out.println(register.get8BitHigher());
        assertEquals("The register's value is not 200", 200, register.get8BitHigher());
    }
}