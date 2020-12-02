package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day01.RunDay01;
import org.jgoeres.adventofcode2020.Day02.RunDay02;

/**
 * Hello world!
 */
public class App {
    static final boolean RUN_ALL = false;

    static final boolean RUN_DAY_1 = false;
    static final boolean RUN_DAY_2 = true;
    static final boolean RUN_DAY_3 = false;
    static final boolean RUN_DAY_4 = false;
    static final boolean RUN_DAY_5 = false;
    static final boolean RUN_DAY_6 = false;
    static final boolean RUN_DAY_7 = false;
    static final boolean RUN_DAY_8 = false;
    static final boolean RUN_DAY_9 = false;
    static final boolean RUN_DAY_10 = false;
    static final boolean RUN_DAY_11 = false;
    static final boolean RUN_DAY_12 = false;
    static final boolean RUN_DAY_13 = false;
    static final boolean RUN_DAY_14 = false;
    static final boolean RUN_DAY_15 = false;
    static final boolean RUN_DAY_16 = false;
    static final boolean RUN_DAY_17 = false;
    static final boolean RUN_DAY_18 = false;
    static final boolean RUN_DAY_19 = false;
    static final boolean RUN_DAY_20 = false;
    static final boolean RUN_DAY_21 = false;
    static final boolean RUN_DAY_22 = false;
    static final boolean RUN_DAY_23 = false;
    static final boolean RUN_DAY_24 = false;
    static final boolean RUN_DAY_25 = true;

    private static long startTime = 0L;
    private static long totalElapsed = 0L;

    public static void main(String[] args) {
        //https://adventofcode.com/2019/

        if (RUN_DAY_1 || RUN_ALL) {
//             Day 01A
            setStartTime();
            RunDay01.problem01A();
            printElapsedTime();

            blankLine();

//            Day 01B
            setStartTime();
            RunDay01.problem01B();
            printElapsedTime();

            blankLine();
        }

        if (RUN_DAY_2 || RUN_ALL) {
//             Day 02A
            setStartTime();
            RunDay02.problem02A();
            printElapsedTime();

            blankLine();

//            Day 02B
            setStartTime();
            RunDay02.problem02B();
            printElapsedTime();

            blankLine();
        }
        System.out.println("\n\nTOTAL ELAPSED TIME:\t" + totalElapsed + " ms");
    }

    private static void blankLine() {
        System.out.println();
    }

    private static void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    private static void printElapsedTime() {
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        totalElapsed += elapsedTime;

        System.out.println("Time elapsed:\t" + elapsedTime + " ms");
    }
}


