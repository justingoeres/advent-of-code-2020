package org.jgoeres.adventofcode2020.Day07;

import java.util.HashMap;
import java.util.HashSet;

public class Bag {
    String name;
    HashMap<Bag, Integer> contains = new HashMap<>();
    HashSet<Bag> containedBy = new HashSet<>();

    public Bag(String name) {
        this.name = name;
    }

    public void addToContains(Integer num, Bag containedBag) {
        // Add the specified bag to the list of bags we contain
        contains.put(containedBag, num);
        // Add ourselves to the list of bags THAT bag is contained by
        containedBag.addToContainedBy(this);
    }

    public void addToContainedBy(Bag containingBag) {
        containedBy.add(containingBag);
    }

    public String getName() {
        return name;
    }

    public HashSet<Bag> getContainedBy() {
        return containedBy;
    }

    @Override
    public String toString() {
        return "Bag: " +name;
    }
}
