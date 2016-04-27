package com.github.jackkell.cpuemulator.util;

/*
A RegisterArg is a type of CommandArg that is used to represent a register value being passed into the argument list
 */
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
