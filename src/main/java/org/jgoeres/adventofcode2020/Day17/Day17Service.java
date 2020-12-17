package org.jgoeres.adventofcode2020.Day17;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17Service {
    private final String DEFAULT_INPUTS_PATH = "data/day17/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();

    public Day17Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day17Service(String pathToFile) {
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

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /** Replace this regex **/
            Pattern p = Pattern.compile("([FB]{7})([LR]{3})");
            while ((line = br.readLine()) != null) {
                // process the line.
                Matcher m = p.matcher(line);
                if (m.find()) { // If our regex matched this line
                    // Parse it
                    String field1 = m.group(1);
                    String field2 = m.group(2);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
