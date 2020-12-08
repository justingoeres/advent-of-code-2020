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
        cpu.reset(); // just in case

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
        /** Fix the program so that it terminates normally by changing
         * exactly one jmp (to nop) or nop (to jmp).
         *
         * What is the value of the accumulator after the program terminates? **/
        // Reset the CPU after Part A
//        cpu.setTestForFix(true);

        boolean done = false;
        ArrayList<Op> originalProgram = (ArrayList<Op>) inputList.clone();

        // Run the program a whole bunch of times, changing JMPs to NOPs and vice versa
        for (int i = 0; i < cpu.getProgramLength(); i++) {
            Op originalOp = cpu.getOp(i);
            if (originalOp instanceof Jmp) {
                // If it's a jump, make it a Nop
                Op newOp = new Nop(originalOp.getArg());
                cpu.setOp(newOp, i);
            } else if (originalOp instanceof Nop) {
                // If it's a Nop, make it an Jmp
                Op newOp = new Jmp(originalOp.getArg());
                cpu.setOp(newOp, i);
            } else if (originalOp instanceof Acc) {
                // If it's an accumulator, don't change anything and skip this trial
                continue;
            }
            // If we get here, we've changed an instruction. Run it and see what happens!
            cpu.reset();
            done = false;
            while (!done) {
                done = cpu.executeNext();
            }
            // When the program ends, check the pc.
            System.out.println(i + "\t" + cpu.getPc() + "\t" + cpu.getAccumulator());
            if (cpu.getPc() == cpu.getTerminationPC()) {
                // We found it!
                result = cpu.getAccumulator();
                System.out.println("Program terminates when opcode at address '" + i + "' is swapped!");
                System.out.println("Final accumulator value:\t" + result);
                break;
            }
            // Reset the program back to what it was before the change
            cpu.setOp(originalOp, i);
        }
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

