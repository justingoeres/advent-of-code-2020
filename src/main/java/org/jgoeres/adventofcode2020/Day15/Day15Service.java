package org.jgoeres.adventofcode2020.Day15;

import java.util.Arrays;

public class Day15Service {
    private final String PROBLEM_INPUT = "0,14,1,3,7,9";

    static boolean DEBUG = true;
    private Game game;

    public Day15Service() {
        loadInputs(PROBLEM_INPUT);
    }

    public Day15Service(String line) {
        loadInputs(line);
    }

    public long doPartA(long targetTurn) {
        long result = 0;
        /** Given your starting numbers, what will be the 2020th number spoken? **/
        long answer = 0L;
        for (long turn = game.getCurrentTurn(); turn < targetTurn; turn++) {   // Start from wherever init ended
            answer = game.calculateNextAnswer();
        }
        result = answer;  // Answer is the last thing spoken when we finish
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }


    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String line) {
            /**
             * Example input:
             * 0,14,1,3,7,9
             * **/
            long[] inputs = Arrays.stream(line.split(","))
                    .mapToLong(num -> Long.parseLong(num)).toArray();
            game = new Game(inputs);
    }
}
