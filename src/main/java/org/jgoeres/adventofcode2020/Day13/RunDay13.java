package org.jgoeres.adventofcode2020.Day13;
public abstract class RunDay13 {
    static String pathToInputs = "data/day13/input.txt";

    static BusService busService = new BusService();

    public static long problem13A() {
        System.out.println("=== DAY 13A ===");

        long result = busService.doPartA();

        System.out.println("Day 13A: Earliest bus * earliest timestamp = " + result);

        return result;
    }

    public static long problem13B() {
        System.out.println("=== DAY 13B ===");

        long result = busService.doPartB();

        System.out.println("Day 13B: Earliest timestamp with buses in perfect order = " + result);

        return result;
    }



}
