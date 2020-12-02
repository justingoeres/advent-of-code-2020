package org.jgoeres.adventofcode2020.Day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Day02Service {
    private final String DAY = "02";
    private final String DEFAULT_INPUTS_PATH = "data/day" + DAY + "/input.txt";

    private ArrayList<Password> inputList = new ArrayList<>();

    public Day02Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day02Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public long countValidPasswordsPartA() {
        Stream<Password> passwordStream = inputList.stream();
        long countValid = passwordStream.filter(password -> password.isValidPartA()).count();

        return countValid;
    }

    public long countValidPasswordsPartB() {
        Stream<Password> passwordStream = inputList.stream();
        long countValid = passwordStream.filter(password -> password.isValidPartB()).count();

        return countValid;
    }

    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                Password nextPassword = new Password(line);

                inputList.add(nextPassword);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
