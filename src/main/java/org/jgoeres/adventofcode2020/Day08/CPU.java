package org.jgoeres.adventofcode2020.Day08;

import java.util.ArrayList;

public class CPU {
    static final boolean DONE = true;
    static final boolean NOT_DONE = false;

    private int pc;
    private int accumulator;

    private ArrayList<Op> program;

    public CPU(ArrayList<Op> program) {
        reset();
        this.program = program;
    }

    public void incrementPC() {
        incrementPC(1);
    }

    public boolean executeNext() {
        // check if we've already executed the current instruction.
        // If so, don't execute – just return true
        if (program.get(pc).isExecuted()) {
            return DONE;
        }
        // execute the instruction at the current program counter
        program.get(pc).execute(this);
        // then return NOT DONE
        return NOT_DONE;
    }

    public void incrementPC(int distance) {
        pc += distance;
    }

    public void accumulateRelative(int distance) {
        accumulator += distance;
    }

    public void reset() {
        pc = 0;
        accumulator = 0;
    }

    public int getAccumulator() {
        return accumulator;
    }
}
