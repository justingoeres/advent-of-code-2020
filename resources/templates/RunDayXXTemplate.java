package org.jgoeres.adventofcode2020.Day${AOC_DAY};
public abstract class RunDay${AOC_DAY} {
    static String pathToInputs = "data/day${AOC_DAY}/input.txt";

    static Day${AOC_DAY}Service day${AOC_DAY}Service = new Day${AOC_DAY}Service();

    public static int problem${AOC_DAY}A() {
        System.out.println("=== DAY ${AOC_DAY}A ===");

        int result = day${AOC_DAY}Service.doPartA();

        System.out.println("Day ${AOC_DAY}A: Answer = " + result);

//        Day ${AOC_DAY}A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem${AOC_DAY}B() {
        System.out.println("=== DAY ${AOC_DAY}B ===");

        int result = day${AOC_DAY}Service.doPartB();

        System.out.println("Day ${AOC_DAY}B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
