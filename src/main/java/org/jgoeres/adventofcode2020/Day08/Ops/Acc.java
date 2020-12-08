package org.jgoeres.adventofcode2020.Day08.Ops;

import org.jgoeres.adventofcode2020.Day08.CPU;
import org.jgoeres.adventofcode2020.Day08.Op;

public class Acc extends Op {
    public Acc(int arg) {
        super(arg);
    }

    @Override
    public void execute(CPU cpu) {
        /**
         * acc increases or decreases a single global value
         * called the accumulator by the value given in the argument.
         *
         * For example, acc +7 would increase the accumulator by 7.
         * The accumulator starts at 0. After an acc instruction,
         * the instruction immediately below it is executed next.
         */
        super.execute(cpu);

        cpu.accumulateRelative((this.getArg()));
        cpu.incrementPC();
    }
}
