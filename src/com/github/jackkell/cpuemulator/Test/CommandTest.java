package com.github.jackkell.cpuemulator.Test;

import com.github.jackkell.cpuemulator.util.Command;
import com.github.jackkell.cpuemulator.cpu.RegisterBank;
import org.junit.Test;

import static com.github.jackkell.cpuemulator.util.Registers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommandTest {
    @Test
    public void testMov() {
        try {
            Command movCommand = new Command("mov eax, 5");
            movCommand.execute();
            long value = RegisterBank.getRegisterValue(rax);
            assertEquals("The mov command does not work with a register and a constant.", 5, value);
        } catch (Exception e) {
            e.printStackTrace();
            fail("The given command format does not exist for mov command");
        }
    }

    @Test
    public void testAdd1() {
        try {
            Command addCommand = new Command("add eax, 5");
            addCommand.execute();
            long value = RegisterBank.getRegisterValue(eax);
            assertEquals("The add command does not work with a register and a constant.", 5, value);
        } catch (Exception e) {
            e.printStackTrace();
            fail("The given command format does not exist for add command");
        }
    }

    @Test
    public void testAdd2() {
        try {
            Command movCommand1 = new Command("mov eax, 5");
            movCommand1.execute();
            Command movCommand2 = new Command("mov ebx, 5");
            movCommand2.execute();
            Command addCommand = new Command("add eax, ebx");
            addCommand.execute();
            long value = RegisterBank.getRegisterValue(eax);
            assertEquals("The add command does not work with a register and register.", 10, value);
        } catch (Exception e) {
            e.printStackTrace();
            fail("The given command format does not exist for add command");
        }
    }

    @Test
    public void testSub() {
        try {
            Command subCommand = new Command("sub eax, 5");
            subCommand.execute();
            long value = RegisterBank.getRegisterValue(eax);
            assertEquals("The sub command does not work with a register and a constant.", -5, value);
        } catch (Exception e) {
            e.printStackTrace();
            fail("The given command format does not exist for sub command");
        }
    }

    @Test
    public void testInc() {
        try {
            Command incCommand = new Command("inc eax");
            incCommand.execute();
            long value = RegisterBank.getRegisterValue(eax);
            assertEquals("The inc command does not work with a register and a constant.", 1, value);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDec() {
        try {
            Command decCommand = new Command("dec eax");
            decCommand.execute();
            long value = RegisterBank.getRegisterValue(eax);
            assertEquals("The dec command does not work with a register and a constant.", -1, value);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
