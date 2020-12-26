package org.jgoeres.adventofcode2020.Day23;

public abstract class RunDay23 {
    static String pathToInputs = "data/day23/input.txt";

    static Day23Service day23Service = new Day23Service();

    public static String problem23A() {
        System.out.println("=== DAY 23A ===");
        final long ONE_HUNDRED = 100L;

        String result = day23Service.doPartA(ONE_HUNDRED);

        System.out.println("Day 23A: Final cup arrangement = " + result);

        return result;
    }

    public static long problem23B() {
        System.out.println("=== DAY 23B ===");
        final long TEN_MILLION = 10000000L;

        long result = day23Service.doPartB(TEN_MILLION);

        System.out.println("Day 23B: Product of two cups clockwise of cup #1 = " + result);

        return result;
    }


}
