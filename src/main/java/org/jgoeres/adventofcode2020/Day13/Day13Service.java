package org.jgoeres.adventofcode2020.Day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day13Service {
    private final String DEFAULT_INPUTS_PATH = "data/day13/input.txt";

    private static final String OUT_OF_SERVICE = "x";
    private static boolean DEBUG = false;

    private ArrayList<Long> busIntervals = new ArrayList<>();
    long earliest;

    public Day13Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day13Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public long doPartA() {
        long result = 0;
        /** What is the ID of the earliest bus you can
         * take to the airport multiplied by the number of
         * minutes you'll need to wait for that bus?
         **/
        long bestBusId = 0;
        long minutesToWaitMin = Long.MAX_VALUE;
        for (Long interval : busIntervals) {
            // How long do we have to wait for this bus?
            long previousArrival = earliest - (earliest % interval); // timestamp of the last bus BEFORE we can get on it.
            long minutesToWait = (previousArrival + interval) - earliest;    // time to wait for the NEXT bus, which we'll be able to get on
            if (minutesToWait < minutesToWaitMin) {
                // If this is the shortest wait we've seen so far
                minutesToWaitMin = minutesToWait;
                bestBusId = interval;
            }
        }
        result = bestBusId * minutesToWaitMin;



        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        busIntervals.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /** Example Input:
             * 1000104
             * 41,x,x,x,x,x...
             * **/
            String line1 = br.readLine();
            // earliest timestamp we can leave
            earliest = Integer.parseInt(line1);
            // process the bus schedule
            String line2 = br.readLine();
            String[] scheduleElements = line2.split(",");
            for (String element : scheduleElements) {
                if (!element.equals(OUT_OF_SERVICE)) {
                    // If this bus is in service
                    // Add it to the known buses
                    busIntervals.add(Long.parseLong(element));
                }
                // else it's out of service; ignore it (for now?)
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
