package org.jgoeres.adventofcode2020.Day08.Ops;

import org.jgoeres.adventofcode2020.Day08.CPU;
import org.jgoeres.adventofcode2020.Day08.Op;

public class Jmp extends Op {
    public Jmp(int arg) {
        super(arg);
    }

    @Override
    public void execute(CPU cpu) {
        /**
         * jmp jumps to a new instruction relative to itself.
         * The next instruction to execute is found using the
         * argument as an offset from the jmp instruction;
         * for example, jmp +2 would skip the next instruction,
         * jmp +1 would continue to the instruction immediately below it,
         * and jmp -20 would cause the instruction 20 lines
         * above to be executed next.
         */
        super.execute(cpu);

//        if (cpu.isTestForFix()) {
//            // Test to see if changing this instruction to a Nop would
//            // cause a termination
//            // Nop would increment the pc by one
////            if (cpu.getPc() + 1 = cpu.getTerminationPC()) {
//            if (cpu.getPc() + 1 >= 628) {
//                System.out.println("FOUND A TERMINATION CHANGING JMP TO NOP; ACCUMULATOR IS " + cpu.getAccumulator());
//            }
//        }
        // jump to an instruction relative to the current pc
        cpu.incrementPC(getArg());
        // do not increment the pc after jumping
    }
}
