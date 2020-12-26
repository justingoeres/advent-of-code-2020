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

    public void insertAfter(Cup toInsert) {
        Cup next = this.getNext();  // store the 'next' cup for a bit
        this.setNext(toInsert); // put the 'toInsert' cup after this one
        toInsert.setNext(next); // set the original 'next' cup to be the 'next' of what we inserted
    }

    public void setNext(Cup next) {
        this.next = next;
        // Also set the 'previous' of the next cup
        next.prev = this;
    }

    public void setPrev(Cup prev) {
        this.prev = prev;
        // Also set the 'next' of the previous cup
        prev.next = this;
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
