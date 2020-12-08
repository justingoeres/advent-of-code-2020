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
//        if (cpu.isTestForFix()) {
//            // Test to see if changing this instruction to a Nop would
//            // cause a termination
//            // Jmp would increment the pc by arg
////            if (cpu.getPc() + getArg() == cpu.getTerminationPC()) {
//                if (cpu.getPc() + getArg() >= 628) {
//                System.out.println("FOUND A TERMINATION CHANGING NOP TO JMP; ACCUMULATOR IS " + cpu.getAccumulator());
//            }
//        }

        cpu.incrementPC();
    }
}
