package org.jgoeres.adventofcode2020.Day14;

import org.jgoeres.adventofcode2020.Day14.Op.Op;
import org.jgoeres.adventofcode2020.Day14.Op.impl.Mask;
import org.jgoeres.adventofcode2020.Day14.Op.impl.SetMemory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14Service {
    private final String DEFAULT_INPUTS_PATH = "data/day14/input.txt";

    private static final Character ZERO = '0';
    private static final Character ONE = '1';
    private static final Character X = 'X';
    private static final long BIT = 1L;
    private static boolean DEBUG = false;

    private ArrayList<Op> opList = new ArrayList<>();

    public Day14Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day14Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public long doPartA() {
        /**
         * Execute the initialization program.
         * What is the sum of all values left in memory after it completes?
         **/
        CPU cpu = new CPU();

        // Run the program
        for (Op op : opList) {
            op.execute(cpu);
        }
        // When done, sum up the memory
        long result = cpu.getMemory().values().stream().reduce(0L, Long::sum);

        return result;
    }

    public long doPartB() {
        CPUv2 cpu = new CPUv2();
        /**
         *  Execute the initialization program using an emulator for a version 2 decoder chip.
         * What is the sum of all values left in memory after it completes?
         * **/
        // Run the program
        for (Op op : opList) {
            op.execute(cpu);
        }
        // When done, sum up the memory
        long result = cpu.getMemory().values().stream().reduce(0L, Long::sum);

        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        opList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /** Example input:
             * mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
             * mem[8] = 11
             * mem[7] = 101
             * mem[8] = 0
             * **/
            // Match mask lines
            Pattern p1 = Pattern.compile("mask = ([X10]+)");
            // Match memory set lines
            Pattern p2 = Pattern.compile("mem\\[(\\d+)] = (\\d+)"); // don't need to escape the closing ']' apparently

            while ((line = br.readLine()) != null) {
                // process the line.
                Matcher m1 = p1.matcher(line);
                Matcher m2 = p2.matcher(line);
                if (m1.find()) {
                    // It's a mask line
                    String maskString = m1.group(1);
                    // reverse it
                    maskString = new StringBuilder(maskString).reverse().toString();
                    long onesMask = 0L;
                    long zeroesMask = 0L;
//                    long floatMask = 0L;
                    ArrayList<Integer> floatMask = new ArrayList<>();
                    // Build the masks
                    for (int i = 0; i < maskString.length(); i++) {
                        // go character by character
                        Character c = maskString.charAt(i);
                        // Make the ones mask
                        long bit = 1;
                        // Put a '1' in the mask for either the ones or the zeroes
                        if (c == ONE) {
                            onesMask += (BIT << i);
                        } else if (c == ZERO) {
                            zeroesMask += (BIT << i);
                        } else if (c == X) {
                            floatMask.add(i);   // add this bit to the list of floating ones
                        }
                    }
                    opList.add(new Mask(onesMask, zeroesMask,floatMask));
                } else if (m2.find()) {
                    // It's a memory set line
                    Long address = Long.parseLong(m2.group(1));
                    Long value = Long.parseLong(m2.group(2));
                    opList.add(new SetMemory(address, value));
                }
            }
        } catch (
                Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
