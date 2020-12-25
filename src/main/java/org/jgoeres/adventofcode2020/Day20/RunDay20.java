package org.jgoeres.adventofcode2020.Day20;

public abstract class RunDay20 {
    static String pathToInputs = "data/day20/input.txt";

    static Day20Service day20Service = new Day20Service();

    public static long problem20A() {
        System.out.println("=== DAY 20A ===");

        long result = day20Service.doPartA();

        System.out.println("Day 20A: Product of all corner tiles = " + result);

        return result;
    }

    public static int problem20B() {
        System.out.println("=== DAY 20B ===");

        int result = day20Service.doPartB(12);

        System.out.println("Day 20B: Water roughness = " + result);

        return result;
    }


}
