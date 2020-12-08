package org.jgoeres.adventofcode2020.Day08;

import java.util.ArrayList;

public class CPU {
    static final boolean DONE = true;
    static final boolean NOT_DONE = false;

    private int pc;
    private int accumulator;

    private int terminationPC;

    private ArrayList<Op> program;

    public CPU(ArrayList<Op> program) {
        this.program = program;
        reset();

        // The program is supposed to terminate by
        // attempting to execute an instruction immediately
        // after the last instruction in the file.
        terminationPC = program.size();
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

        // Check to see if the next instruction would be past the
        // end of the program. If so, terminate
        if (pc >= terminationPC) {
            return DONE;
        }
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
        for (Op op : program) {
            op.reset();
        }
    }

    public int getAccumulator() {
        return accumulator;
    }

    public int getPc() {
        return pc;
    }

    public int getTerminationPC() {
        return terminationPC;
    }

    public int getProgramLength() {
        return program.size();
    }

    public Op getOp(int address) {
        return program.get(address);
    }

    public void setOp(Op op, int address) {
        program.set(address, op);
    }

}
