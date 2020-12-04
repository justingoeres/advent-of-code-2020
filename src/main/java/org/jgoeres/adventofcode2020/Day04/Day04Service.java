package org.jgoeres.adventofcode2020.Day04;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day04Service {
    private final String DEFAULT_INPUTS_PATH = "data/day04/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();

    public Day04Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day04Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /** Put problem implementation here **/
        
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/
        
        return result;
    }

    // load inputs line-by-line as list of Integers 
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            while ((line = br.readLine()) != null) {
                // process the line.
                nextInt = Integer.parseInt(line);

                inputList.add(nextInt);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
