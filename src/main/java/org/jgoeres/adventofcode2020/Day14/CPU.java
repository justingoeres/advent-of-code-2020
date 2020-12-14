package org.jgoeres.adventofcode2020.Day14;

import java.util.ArrayList;
import java.util.HashMap;

public class CPU {
    protected static final Long ZERO = 0L;
    protected static final Long ONE = 1L;
    protected HashMap<Long, Long> memory = new HashMap<>();

    // init the masks
    protected Long onesMask = ZERO;
    protected Long zeroesMask = ZERO;
    //    protected Long floatMask = ZERO;
    protected ArrayList<Integer> floatMask;

    public void setMemory(Long address, Long value) {
        // Set the specified memory value, but handle the masks!
        // onesMask must keep all digits but replace the masked bits with 1s
        // so we *OR* the mask with the value
        value |= onesMask;
        // zeroesMask must keep all digital but replace the masked bits with 0s
        // so we *AND* the OPPOSITE of the mask with the value
        value &= ~zeroesMask;
        // Then stick the value in memory
        memory.put(address, value);
    }

    public void setMasks(Long onesMask, Long zeroesMask, ArrayList<Integer> floatMask) {
        setOnesMask(onesMask);
        setZeroesMask(zeroesMask);
        setFloatMask(floatMask);
    }

    public void setOnesMask(Long onesMask) {
        this.onesMask = onesMask;
    }

    public void setZeroesMask(Long zeroesMask) {
        this.zeroesMask = zeroesMask;
    }

    public void setFloatMask(ArrayList<Integer> floatMask) {
        this.floatMask = floatMask;
    }

    public HashMap<Long, Long> getMemory() {
        return memory;
    }

    public void reset() {
        onesMask = ZERO;
        zeroesMask = ZERO;
        memory.clear();
    }
}
