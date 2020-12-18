package org.jgoeres.adventofcode2020.Day18;

public class ParenGroup {
    int begin;
    int end;

    public ParenGroup() {
    }

    public ParenGroup(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
