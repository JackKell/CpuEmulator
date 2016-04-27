package com.github.jackkell.cpuemulator.util;

import java.util.Objects;

/*
The Operation enum represents the list of commands that the C.A.x.E emulator supports
 */
public enum Operation {
    dump, mov, push, pop, add, sub, inc,
    dec, imul, idiv, and, or, xor, not,
    neg, shl, shr, lea, cmp, cld, std,
    data;

    // Check if a given string is an enum in Operation
    public static boolean contains(String name) {
        for (Operation operation : Operation.values()) {
            if (Objects.equals(operation.name(), name)) {
                return true;
            }
        }
        return false;
    }
}
