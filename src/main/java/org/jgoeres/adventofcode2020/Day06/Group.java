package org.jgoeres.adventofcode2020.Day06;

import java.util.HashSet;

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
}

