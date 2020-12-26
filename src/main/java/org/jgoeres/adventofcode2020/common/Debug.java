package org.jgoeres.adventofcode2020.common;

public abstract class Debug {
    public static void debugPrint(boolean debug, String output) {
        if (debug) {
            System.out.println(output);
        }
    }
}
