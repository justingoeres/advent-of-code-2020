package org.jgoeres.adventofcode2020.Day12;

public abstract class RunDay12 {
    static String pathToInputs = "data/day12/input.txt";

    static ShipMovementService shipMovementService = new ShipMovementService();

    public static int problem12A() {
        System.out.println("=== DAY 12A ===");

        int result = shipMovementService.doPartA();

        System.out.println("Day 12A: Ship final location (Manhattan distance) = " + result);

        return result;
    }

    public static int problem12B() {
        System.out.println("=== DAY 12B ===");

        int result = shipMovementService.doPartB();

        System.out.println("Day 12B: Ship final location (Manhattan distance) = " + result);

        return result;
    }
}
