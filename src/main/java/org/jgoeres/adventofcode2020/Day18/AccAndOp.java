package org.jgoeres.adventofcode2020.Day18;

import java.math.BigInteger;

public class AccAndOp {
    BigInteger accumulator = BigInteger.ZERO;
    Op operation = Op.ADD;

    public AccAndOp() {
    }

    public AccAndOp(BigInteger accumulator, Op operation) {
        this.accumulator = accumulator;
        this.operation = operation;
    }
}
