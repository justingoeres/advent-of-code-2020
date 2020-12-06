package org.jgoeres.adventofcode2020.Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05Service {
    private final String DEFAULT_INPUTS_PATH = "data/day05/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();

    public Day05Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day05Service(String pathToFile) {
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
            // Example line:
            //  BFFBFBBRLR
            String line;
            Pattern p = Pattern.compile("([FB]{7})([LR]{3})");
            int maxResult = Integer.MIN_VALUE;
            while ((line = br.readLine()) != null) {
                // process the line.

                Matcher m = p.matcher(line);
                if (m.find()) {
                    // Parse the seat location
                    String rowString = m.group(1);
                    String columnString = m.group(2);

                    // Calculate the row number
                    int rowNum = 0;
                    for (byte rowChar : rowString.getBytes()) {
                        rowNum = rowNum << 1;
                        rowNum += ((rowChar == 'B') ? 1 : 0);
                    }
//                    System.out.println("Row:\t" + rowNum);

                    // Calculate the column number
                    int columnNum = 0;
                    for (byte columnChar : columnString.getBytes()) {
                        columnNum = columnNum << 1;
                        columnNum += ((columnChar == 'R') ? 1 : 0);
                    }
//                    System.out.println("Column:\t" + columnNum);

                    int result = rowNum * 8 + columnNum;
                    System.out.println("Result:\t" + result);

                    if (result > maxResult) maxResult = result;
                }
            }
            System.out.println("Max Result:\t" + maxResult);
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
