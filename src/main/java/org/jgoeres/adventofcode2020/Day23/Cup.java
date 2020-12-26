package org.jgoeres.adventofcode2020.Day23;

public class Cup {
    int id;
    Cup prev;
    Cup next;

    public Cup(int id) {
        this.id = id;
    }

    public Cup(int id, Cup prev) {
        this.id = id;
        this.prev = prev;
        // Also set the 'next' of the previous cup
        prev.next = this;
    }

    public void setNext(Cup next) {
        this.next = next;
        // Also set the 'previous' of the next cup
        next.prev = this;
    }

    public int getId() {
        return id;
    }

    public Cup getPrev() {
        return prev;
    }

    public Cup getNext() {
        return next;
    }
}
