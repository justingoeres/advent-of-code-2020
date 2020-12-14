package org.jgoeres.adventofcode2020.Day14;

import java.util.HashMap;

public class CPU {
    private static final Long ZERO = 0L;
    private HashMap<Long, Long> memory = new HashMap<>();

    // init the masks
    private Long onesMask = ZERO;
    private Long zeroesMask = ZERO;

    public Long getMemory(Long address) {
        if (memory.containsKey(address)) {
            return memory.get(address);
        } else {
            return ZERO;
        }
    }

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

    public void setMasks(Long onesMask, Long zeroesMask) {
        setOnesMask(onesMask);
        setZeroesMask(zeroesMask);
    }

    public void setOnesMask(Long onesMask) {
        this.onesMask = onesMask;
    }

    public void setZeroesMask(Long zeroesMask) {
        this.zeroesMask = zeroesMask;
    }

    public HashMap<Long, Long> getMemory() {
        return memory;
    }

    public void reset(){
        onesMask = ZERO;
        zeroesMask = ZERO;
        memory.clear();
    }
}
