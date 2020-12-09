package org.jgoeres.adventofcode2020.Day09;

public abstract class RunDay09 {
    static String pathToInputs = "data/day09/input.txt";

    static Day09Service day09Service = new Day09Service();

    static final int DEFAULT_PREAMBLE = 25;

    public static long problem09A() {
        return problem09A(DEFAULT_PREAMBLE);
    }

    public static long problem09A(int preambleLength) {
        /** The first step of attacking the weakness in the XMAS data is to
         * find the first number in the list (after the preamble) which is
         * not the sum of two of the 25 numbers before it.
         *
         * What is the first number that does not have this property?
         * **/
        System.out.println("=== DAY 09A ===");

        long result = day09Service.doPartA(preambleLength);

        System.out.println("Day 09A: First value that does NOT meet encoding requirements = " + result);

        return result;
    }

    public static int problem09B() {
        System.out.println("=== DAY 09B ===");

        int result = day09Service.doPartB();

        System.out.println("Day 09B: Answer = " + result);

        return result;
    }


}
