package org.jgoeres.adventofcode2020.Day02;

public abstract class RunDay02 {
    static String DAY = "02";

    static String pathToInputs = "data/day" + DAY + "/input.txt";

    static Day02Service day02Service = new Day02Service();

    public static long problem02A() {
        // Day 02A
        System.out.println("=== DAY " + DAY + "A ===");

        long result = day02Service.countValidPasswords();

        System.out.println("Day " + DAY + "A: Number of valid passwords = " + result);

//        Day 02A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem02B() {
        // Day 02B
        System.out.println("=== DAY " + DAY + "B ===");

        int result = 0;

        System.out.println("Day " + DAY + "B: Answer = " + result);

//        Day 02B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

}
