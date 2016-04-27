package com.github.jackkell.cpuemulator.util;

import java.util.Objects;

/*
Register is an enum that represents all of the possible registers that the user can type in along with their
corresponding group and size.
 */
public enum Registers {
    rax("rax", 64), rbx("rbx", 64), rcx("rcx", 64),
    rdx("rdx", 64), rbp("rbp", 64), rsi("rsi", 64),
    rdi("rdi", 64), rsp("rsp", 64), rip("rip", 64),

    eax("rax", 32), ebx("rbx", 32), ecx("rcx", 32),
    edx("rdx", 32), ebp("rbp", 32), esi("rsi", 32),
    edi("rdi", 32), esp("rsp", 32), eip("rip", 32),

    ax("rax", 16), bx("rbx", 16), cx("rcx", 16),
    dx("rdx", 16), bp("rbp", 16), si("rsi", 16),
    di("rdi", 16), sp("rsp", 16), ip("rip", 16),

    ah("rax", 8), bh("rbx", 8), ch("rcx", 8),
    dh("rdx", 8),

    al("rax", 8), bl("rbx", 8), cl("rcx", 8),
    dl("rdx", 8), bpl("rbp", 8), sil("rsi", 8),
    spl("rsp", 8), dil("rdi", 8),

    cs("cs", 16), ds("ds", 16), es("es", 16),
    fs("fs", 16), gs("gs", 16), ss("ss", 16);

    private final int size;
    private final String group;
    Registers(String group, int size) {
        this.group = group;
        this.size = size;
    }

    public String group() {return group;}
    public int size() {return size;}

    // Checks if given register name is contained with the Registers enum
    public static boolean contains(String name) {
        for (Registers register : Registers.values()) {
            if (Objects.equals(register.name(), name)) {
                return true;
            }
        }
        return false;
    }
}
