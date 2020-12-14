package org.jgoeres.adventofcode2020.Day14;

import java.util.ArrayList;
import java.util.HashSet;

public class CPUv2 extends CPU {
    ArrayList<Long> masks = new ArrayList<>();

    @Override
    public void setMemory(Long address, Long value) {
        /** A version 2 decoder chip doesn't modify the values being written at all.
         * Instead, it acts as a memory address decoder.
         *
         * Immediately before a value is written to memory, each bit in the bitmask
         * modifies the corresponding bit of the destination memory address in the following way:
         *   If the bitmask bit is 0, the corresponding memory address bit is unchanged.
         *   If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
         *   If the bitmask bit is X, the corresponding memory address bit is floating.
         *
         * A floating bit is not connected to anything and instead fluctuates unpredictably.
         * In practice, this means the floating bits will take on all possible values,
         * potentially causing many memory addresses to be written all at once!
         **/

        // If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
        Long targetAddress = address | onesMask;
        // If the bitmask bit is X, the corresponding memory address bit is floating.
        // Build all the possible addresses implied by the floatMask
        masks.clear();
        for (long i = 0; i < Math.pow(2, floatMask.size()); i++) {
            // First build all of them, 0 - ((2^number of X bits)-1)
            masks.add(i);
        }
        // For each of those possible address masks, shift the corresponding bit into
        // place in targetAddress to build the final set of addresses to write
        for (int i = 0; i < masks.size(); i++) {
            Long maskedAddress = targetAddress;
            Long currentMask = masks.get(i);
            // for each 'X' bit in the mask
            for (Integer maskBitPosition : floatMask) {
                // maskBit gives the bit POSITION that floats, so move the corresponding
                // bit of currentMask into that position of targetAddress and put that new
                // targetAddress into our list of memory addresses to finally write
                Long maskBitValue = currentMask & 1L;
                if(maskBitValue == ZERO) {
                    maskedAddress = maskedAddress & ~(1L << maskBitPosition);
                 } else if (maskBitValue == ONE) {
                    maskedAddress = maskedAddress | (1L << maskBitPosition);
                }
                // Shift currentMask down and keep going
                currentMask >>= 1;
            }
            memory.put(maskedAddress, value); // A version 2 decoder chip doesn't modify the values being written at all.
        }
    }
}
