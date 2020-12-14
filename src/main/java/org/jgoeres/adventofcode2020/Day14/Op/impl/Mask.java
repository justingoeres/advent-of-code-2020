package org.jgoeres.adventofcode2020.Day14.Op.impl;

import org.jgoeres.adventofcode2020.Day14.CPU;
import org.jgoeres.adventofcode2020.Day14.Op.Op;

import java.util.ArrayList;

public class Mask implements Op {
    // init the masks
    private Long onesMask;
    private Long zeroesMask;
//    private Long floatMask;
    private ArrayList<Integer> floatMask;

    public Mask(Long onesMask, Long zeroesMask,ArrayList<Integer> floatMask) {
        this.onesMask = onesMask;
        this.zeroesMask = zeroesMask;
        this.floatMask = floatMask;
    }

    @Override
    public void execute(CPU cpu) {
        cpu.setMasks(onesMask, zeroesMask,floatMask);
    }
}
