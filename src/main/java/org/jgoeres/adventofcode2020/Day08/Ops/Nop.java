package org.jgoeres.adventofcode2020.Day08.Ops;

import org.jgoeres.adventofcode2020.Day08.CPU;
import org.jgoeres.adventofcode2020.Day08.Op;

public class Nop extends Op {
    public Nop(int arg) {
        super(arg);
    }

    @Override
    public void execute(CPU cpu) {
        /**
         * nop stands for No OPeration - it does nothing.
         * The instruction immediately below it is executed next.
         */
        super.execute(cpu);
        cpu.incrementPC();
    }
}
