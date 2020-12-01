package org.jgoeres.adventofcode2020.common.intcode;


public interface IOpCode {
    boolean execute(Instruction instruction);
}
