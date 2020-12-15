package org.jgoeres.adventofcode2020.Day15;

import java.util.HashMap;

import static org.jgoeres.adventofcode2020.Day15.Day15Service.DEBUG;

public class Game {
    private static final long ZERO = 0L;
    private HashMap<Long, Answer> history = new HashMap<>(); // answerValue -> Answer info (turn last spoken, etc.)
    private long currentTurn;
    private Answer previousAnswer = null;

    public Game(long[] inputs) {
        initGame(inputs);
    }

    private void initGame(long[] inputs) {
        // Seed the game state with the inputs
        currentTurn = 0L;   // Start at turn #1
        for (long input : inputs) {
            Answer newAnswer = new Answer(currentTurn);
            history.put(input, newAnswer);   // Put this number in the state
            previousAnswer = newAnswer;
            currentTurn++;  // increment the turn
        }
    }

    public long calculateNextAnswer() {
        /**
         * each turn consists of considering the most recently spoken number:
         *
         * If that was the first time the number has been spoken, the current player says 0.
         * Otherwise, the number had been spoken before; the current player announces
         * how many turns apart the number is from when it was previously spoken.
         */
        long newAnswerValue;
        Answer nextAnswer;
        // Had the previous answer been said before
        if (previousAnswer.hadBeenSaid()) {
            // The number HAD been spoken before
            // How long had it been since it was last spoken?
            newAnswerValue = previousAnswer.getTimeBetween();
        } else {
            newAnswerValue = ZERO;
        }
        // Update the info for newAnswerValue, or create it if it doesn't exist yet
        if (history.containsKey(newAnswerValue)) {
            // It exists; get it
            nextAnswer = history.get(newAnswerValue);
            // Update it so we know we're saying it this turn
            nextAnswer.setNewPrevious1(currentTurn);
        } else {
            // Doesn't exist; create it
            nextAnswer = new Answer(currentTurn); //
            // Say how many turns it has been
            history.put(newAnswerValue, nextAnswer);
        }
        if (DEBUG) {
            System.out.println("Turn #\t" + currentTurn + "\tSpoken:\t" + newAnswerValue);
        }

        previousAnswer = nextAnswer;
        currentTurn++;  // Increment to next turn
        return newAnswerValue;
    }

    public long getCurrentTurn() {
        return currentTurn;
    }
}
