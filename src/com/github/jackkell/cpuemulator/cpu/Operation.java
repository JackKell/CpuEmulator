package com.github.jackkell.cpuemulator.cpu;

import java.util.Objects;

public enum Operation {
    dump, mov, push, pop, add, sub, inc,
    dec, imul, idiv, and, or, xor, not,
    neg, shl, shr, lea, cmp, cld, std,
    data;

    public static boolean contains(String name) {
        for (Operation operation : Operation.values()) {
            if (Objects.equals(operation.name(), name)) {
                return true;
            }
        }
        return false;
    }
}
