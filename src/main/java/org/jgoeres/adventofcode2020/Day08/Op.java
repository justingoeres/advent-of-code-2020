package org.jgoeres.adventofcode2020.Day08;

public class Op {
    int arg;
    boolean executed = false;

    public Op() {
    }

    public Op(int arg) {
        this.arg = arg;
    }

    public void execute(CPU cpu) {
        executed = true;
    }

    protected int getArg() {
        return arg;
    }

    public boolean isExecuted() {
        return executed;
    }
}
