package org.jgoeres.adventofcode2020.Day08;

import org.jgoeres.adventofcode2020.Day08.Ops.Acc;
import org.jgoeres.adventofcode2020.Day08.Ops.Jmp;
import org.jgoeres.adventofcode2020.Day08.Ops.Nop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08Service {
    private final String DEFAULT_INPUTS_PATH = "data/day08/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Op> inputList = new ArrayList<>();
    private CPU cpu;
    public Day08Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day08Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /** Run your copy of the boot code.
         *
         * Immediately before any instruction is executed a second time,
         * what value is in the accumulator?
         **/

        boolean done = false;
        // Run the program
        while (!done) {
            done = cpu.executeNext();
        }
        // when we're done, read the accumulator
        result = cpu.getAccumulator();
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            /**
             * Example inputs:
             * acc +33
             * acc -7
             * acc +39
             * jmp +214
             */
            String line;
            Integer nextInt = 0;
            /** Replace this regex **/
            Pattern p = Pattern.compile("(\\w{3}) ([+-]\\d+)");
            while ((line = br.readLine()) != null) {
                // process the line.
                Matcher m = p.matcher(line);
                if (m.find()) { // If our regex matched this line
                    // Parse it
                    String op = m.group(1);
                    int arg = Integer.parseInt(m.group(2));

                    Op newOp;
                    // Create the operation
                    switch (op) {
                        case "acc":
                            newOp = new Acc(arg);
                            break;
                        case "jmp":
                            newOp = new Jmp(arg);
                            break;
                        case "nop":
                            newOp = new Nop(arg);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + op);
                    }
                    // Add it to the program
                    inputList.add(newOp);
                }
            }
            // When we're done, set up the CPU
            cpu = new CPU(inputList);
        } catch (
                Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}

