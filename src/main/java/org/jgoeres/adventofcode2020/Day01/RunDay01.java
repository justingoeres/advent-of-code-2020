package org.jgoeres.adventofcode2020.Day01;

public abstract class RunDay01 {
    static String DAY = "1";

    static String pathToInputs = "data/day" + DAY + "/input.txt";

    static Day01Service day01Service = new Day01Service();

    static final int TARGET = 2020;

    public static int problem01A() {
        System.out.println("=== DAY " + DAY + "A ===");

        int result = day01Service.calculateMissingExpense(TARGET);

        System.out.println("Day " + DAY + "A: Answer = " + result);

//        Day XXA: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem01B() {
        System.out.println("=== DAY " + DAY + "B ===");

        int result = day01Service.calculateMissingExpenseThreeArguments(TARGET);

        System.out.println("Day " + DAY + "B: Answer = " + result);

//        Day XXB: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

}
