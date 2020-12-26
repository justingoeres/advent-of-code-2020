package org.jgoeres.adventofcode2020.Day23;

public abstract class RunDay23 {
    static String pathToInputs = "data/day23/input.txt";

    static Day23Service day23Service = new Day23Service();

    public static int problem23A() {
        System.out.println("=== DAY 23A ===");
        final long LIMIT = 100L;

        int result = day23Service.doPartA(LIMIT);

        System.out.println("Day 23A: Answer = " + result);

        return result;
    }

    public static int problem23B() {
        System.out.println("=== DAY 23B ===");

        int result = day23Service.doPartB();

        System.out.println("Day 23B: Answer = " + result);

        return result;
    }


}
