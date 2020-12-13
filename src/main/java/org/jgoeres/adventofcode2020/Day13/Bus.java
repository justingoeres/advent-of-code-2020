package org.jgoeres.adventofcode2020.Day13;

public class Bus {
long interval;
long gap;

    public Bus(long interval, long gap) {
        this.interval = interval;
        this.gap = gap;
    }

    public long getInterval() {
        return interval;
    }

    public long getGap() {
        return gap;
    }
}
