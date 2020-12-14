package org.jgoeres.adventofcode2020.Day14.Op.impl;

import org.jgoeres.adventofcode2020.Day14.CPU;
import org.jgoeres.adventofcode2020.Day14.Op.Op;

public class Mask implements Op {
    // init the masks
    private Long onesMask = ZERO;
    private Long zeroesMask = ZERO;

    public Mask(Long onesMask, Long zeroesMask) {
        this.onesMask = onesMask;
        this.zeroesMask = zeroesMask;
    }

    @Override
    public void execute(CPU cpu) {
        cpu.setMasks(onesMask, zeroesMask);
    }
}
