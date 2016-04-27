package com.github.jackkell.cpuemulator.util;

import com.github.jackkell.cpuemulator.cpu.Registers;

public class RegisterArg extends CommandArg {
    private Registers register;


    public RegisterArg(Registers register) {
        super(register.size());
        this.register = register;
    }

    public Registers getRegister() {
        return register;
    }
}
