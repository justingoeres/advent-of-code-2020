package org.jgoeres.adventofcode2020.Day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day13Service {
    private final String DEFAULT_INPUTS_PATH = "data/day13/input.txt";

    private static final String OUT_OF_SERVICE = "x";
    private static boolean DEBUG = false;

    private ArrayList<Bus> buses = new ArrayList<>();
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
        for (Bus bus : buses) {
            long interval = bus.getInterval();
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

    public long doPartB() {
        /** What is the earliest timestamp such that all of the
         * listed bus IDs depart at offsets matching their positions in the list?
         **/

        // Work through the list of buses pairwise. Find the timestamp that satisfies the conditions
        // for those TWO buses, and the INTERVAL that the condition occurs at.
        // Then use that starting timestamp, and that interval, to search for the condition for the NEXT bus.
        // Repeat until we're done

        long interval = buses.get(0).getInterval(); // initial step is the interval of the FIRST bus
        long t = interval;   // start at t= 0 + the first step (t=0 is degenerate)
        int j = 0;  // For debug output
        for (int i = 1; i < buses.size(); i++) {
            // Do each bus in order
            Bus nextBus = buses.get(i);
            interval = Math.max(interval, nextBus.getInterval());
            while (true) {  // go until we find the solution
                if (DEBUG) {
                    System.out.println("Iteration # " + j + ":\tt = " + t + " ; Step = " + interval + "\t(Bus # " + i + ")");
                    j++;
                }
                // We're looking for time t at which (t + gap) is an even multiple of nextBus's interval
                if (((t + nextBus.getInterval() + nextBus.getGap())
                        % nextBus.getInterval()) == 0) {
                    // We found the time t where these buses are synced up (separated by 'gap')
                    // So now keep looking forward, starting from the current time
                    // and stepping by the interval of the next bus
                    break;
                }
                t += interval;  // step the time
            }
            // Because everything is relatively prime, the next interval is the product of the two intervals so far
            interval = interval * nextBus.getInterval();
        }
        return t;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        buses.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
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
            for (int i = 0; i < scheduleElements.length; i++) {
                String element = scheduleElements[i];
                if (!element.equals(OUT_OF_SERVICE)) {
                    // If this bus is in service
                    // Add it to the known buses
                    Bus bus = new Bus(Long.parseLong(element), i);
                    buses.add(bus);
                }
                // else it's out of service; ignore it
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
