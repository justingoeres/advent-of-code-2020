package org.jgoeres.adventofcode2020.Day16;
public abstract class RunDay16 {
    static String pathToInputs = "data/day16/input.txt";

    static Day16Service day16Service = new Day16Service();

    public static int problem16A() {
        System.out.println("=== DAY 16A ===");

        int result = day16Service.doPartA();

        System.out.println("Day 16A: Ticket error rate = " + result);

        return result;
    }

    public static long problem16B() {
        System.out.println("=== DAY 16B ===");

        long result = day16Service.doPartB();

        System.out.println("Day 16B: Product of all 'departure' fields = " + result);

        return result;
    }



}
