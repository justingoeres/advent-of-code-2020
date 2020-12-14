package org.jgoeres.adventofcode2020.Day14.Op.impl;

import org.jgoeres.adventofcode2020.Day14.CPU;
import org.jgoeres.adventofcode2020.Day14.Op.Op;

public class SetMemory implements Op {
    private Long address = ZERO;
    private Long value = ZERO;

    public SetMemory(Long address, Long value) {
        this.address = address;
        this.value = value;
    }

    @Override
    public void execute(CPU cpu) {
        cpu.setMemory(address,value);
    }
}
