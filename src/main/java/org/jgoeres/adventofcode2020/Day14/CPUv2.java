package org.jgoeres.adventofcode2020.Day14;

import java.util.ArrayList;

public class CPUv2 extends CPU {
    ArrayList<Long> masks = new ArrayList<>();
    ArrayList<Long> shiftedMasks = new ArrayList<>();

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
//
//        masks.clear();
//        shiftedMasks.clear();
//        // Build all the possible addresses implied by the floatMask
//        for (long i = 0; i < Math.pow(2, floatMask.size()); i++) {
//            // First build all of them, 0 - ((2^number of X bits)-1)
//            masks.add(i);
//        }
//        // For each of the masks, spread the bits out
//        // and
//
//
//        // For each of the masks, spread the bits out according to which bits need to actually be generated
//        for (int i = 0; i < masks.size(); i++) {
//            Long shiftedMask = Long.MAX_VALUE;
//            // For every item in the masks list
//            Long currentMask = masks.get(i);
//            for (int bitPosition = 0; bitPosition < floatMask.size(); bitPosition++) {
//                // Get the leftmost bit of currentMask
//                Long bitValue = currentMask & 1L;
//                // Shift it into position
//                bitValue <<= floatMask.get(bitPosition);
//                // Add it to the current shifted mask value
//                shiftedMask += bitValue;
//                // Shift currentMask to the right so we can repeat the process for the next bit
//                currentMask >>= 1;
//            }
//            // Add the shifted mask into the list
//            shiftedMasks.add(shiftedMask);
//        }

        // Now that we've got all the memory masks shifted into place,
        // process the target memory address through them to determine
        // the destination addresses we're going to write to
        /**
         * Immediately before a value is written to memory, each bit in the
         * bitmask modifies the corresponding bit of the destination memory
         * address in the following way:
         *
         * If the bitmask bit is 0, the corresponding memory address bit is unchanged.
         * If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
         * If the bitmask bit is X, the corresponding memory address bit is floating.
         */
//        for (Long addressMask : shiftedMasks) {
//            // If the bitmask bit is 1, the corresponding memory address bit
//            // is overwritten with 1.
//            Long targetAddress = address | onesMask; // bitmask bit == 1, overwrite address bit with 1
////            targetAddress = address | onesMask | addressMask;
//            targetAddress &= addressMask;   // bitmask bit ==X, the corresponding memory address bit is floating.
////            setMemory(targetAddress, value); // A version 2 decoder chip doesn't modify the values being written at all.
//            // Then stick the value in memory
//            memory.put(targetAddress, value); // A version 2 decoder chip doesn't modify the values being written at all.
//
//        }
    }


}
