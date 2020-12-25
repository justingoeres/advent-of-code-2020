package org.jgoeres.adventofcode2020.Day21;

import java.util.HashSet;

public class Food {
    int id;
    HashSet<String> ingredients;
    HashSet<String> allergens;

    public Food(int id, HashSet<String> ingredients, HashSet<String> allergens) {
        this.id = id;
        this.ingredients = ingredients;
        this.allergens = allergens;
    }
}
