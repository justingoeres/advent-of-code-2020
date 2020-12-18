package org.jgoeres.adventofcode2020.Day18;

public class AccAndOp {
    long accumulator = 0;
    Op operation = Op.ADD;

    public AccAndOp() {
    }

    public AccAndOp(long accumulator, Op operation) {
        this.accumulator = accumulator;
        this.operation = operation;
    }
}
