package org.jgoeres.adventofcode2020.Day06;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Group {
    private HashSet<Passenger> passengers = new HashSet<>();

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public int totalYesAnswers() {
        int total = 0;
        HashSet<Character> totalAnswers = new HashSet<>();
        for (Passenger p : passengers) {
            // Union all passengers' answers together...
            totalAnswers.addAll(p.getAnswers());
        }
        // ... then count how many that is, total.
        total = totalAnswers.size();
        return total;
    }

    public int everyoneYesAnswers() {
        int total = 0;
        Set<Character> commonAnswers = new HashSet<>();
        boolean firstPassenger = true;
        for (Passenger p : passengers) {
            if (firstPassenger) {
                // If this is the first iteration,
                // just init the set to the first person's answers
                commonAnswers = p.getAnswers();
                firstPassenger = false;
            } else {
                // Remove from the commonAnswers everything that the current passenger didn't ALSO
                // answer Yes to.
                commonAnswers = commonAnswers.stream()
                        .filter(answers -> p.getAnswers().contains(answers))
                        .collect(Collectors.toSet());
            }
        }
        // ... then count how many that is, total.
        total = commonAnswers.size();
        return total;
    }
}

