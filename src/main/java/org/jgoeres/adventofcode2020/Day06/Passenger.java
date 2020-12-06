package org.jgoeres.adventofcode2020.Day06;

import java.util.HashSet;

public class Passenger {
    private HashSet<Character> answers = new HashSet<>();

    public void addAnswer(Character answer) {
        answers.add(answer);
    }

    public HashSet<Character> getAnswers() {
        return answers;
    }

    public void setAnswers(HashSet<Character> answers) {
        this.answers = answers;
    }
}
