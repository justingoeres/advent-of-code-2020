package org.jgoeres.adventofcode2020.Day18;

import java.math.BigInteger;

public abstract class RunDay18 {
    static String pathToInputs = "data/day18/input.txt";

    static Day18Service day18Service = new Day18Service();

    public static BigInteger problem18A() {
        System.out.println("=== DAY 18A ===");

        BigInteger result = day18Service.doPartA();

        System.out.println("Day 18A: Answer = " + result);

        return result;
    }

    public static BigInteger problem18B() {
        System.out.println("=== DAY 18B ===");

        BigInteger result = day18Service.doPartB();

        System.out.println("Day 18B: Answer = " + result);

        return result;
    }



}
