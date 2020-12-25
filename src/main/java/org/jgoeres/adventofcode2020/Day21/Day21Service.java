package org.jgoeres.adventofcode2020.Day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21Service {
    private final String DEFAULT_INPUTS_PATH = "data/day21/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();
    private HashMap<String, HashSet<String>> allergensToIngredientsMap = new HashMap<>();
    private HashMap<String, HashSet<String>> ingredientsMayContain = new HashMap<>();
    private HashMap<Integer, Food> foods = new HashMap<>();
    private HashSet<String> allIngredients = new HashSet<>();

    public Day21Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day21Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /**
         * Determine which ingredients cannot possibly contain any of the allergens in your list.
         *
         * How many times do any of those ingredients appear?
         **/


        // For each allergen, find the ingredients that appear in EVERY line containing that allergen
        // Look at every food, keep track of which ingredients appear every time
        for (Map.Entry<String, HashSet<String>> entry : allergensToIngredientsMap.entrySet())
            for (Food food : foods.values()) {
                if (food.allergens.contains(entry.getKey())) {
                    // If this food contains this allergen
                    // Keep only the intersection of this food's ingredients with the ones that MAY contain this allergen
                    entry.getValue().retainAll(food.ingredients);
                }
            }
        // Now we know all the ingredients that MIGHT contain one or more allergens.
        // So remove all of them from the full ingredients list
        for (HashSet<String> ingredients : allergensToIngredientsMap.values()) {
            // Remove these ingredients from the allIngredients list
            allIngredients.removeAll(ingredients);
        }
        // Whatever is left in allIngredients can't be in ANY allergen list
        // Count how many times they appear in all the foods
        int count = 0;
        for (Food food : foods.values()) {
            food.ingredients.retainAll(allIngredients);
            count += food.ingredients.size();
        }
        return count;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /**
             * Example:
             *  mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
             *  trh fvjkl sbzzf mxmxvkd (contains dairy)
             *  sqjhc fvjkl (contains soy)
             *  sqjhc mxmxvkd sbzzf (contains fish)
             * **/
            int i = 0;
            Pattern p = Pattern.compile("(\\w+)[,)]");  // e.g. "contains dairy, fish)"
            while ((line = br.readLine()) != null) {
                // process the line.
                // First, split the ingredients line at the '('
                String[] split = line.split("\\(");
                String ingredientsString = split[0];
                String allergensString = split[1];
                // Collect all the ingredients in this line
                HashSet<String> ingredients = new HashSet<>();
                ingredients.addAll(Arrays.asList(ingredientsString.trim().split(" ")));
                // Collect all the allergens in this line
                HashSet<String> allergens = new HashSet<>();
                Matcher m = p.matcher(allergensString);
                while (m.find()) allergens.add(m.group(1));

                // Build up our collection of ingredients and which allergens they MAY contain
                for (String ingredient : ingredients) {
                    HashSet<String> mayContain;
                    allIngredients.add(ingredient);
                    if (ingredientsMayContain.containsKey(ingredient)) {
                        // If we've seen this ingredient before
                        mayContain = ingredientsMayContain.get(ingredient);
                    } else {
                        // New ingredient, make a new (empty) allergen set
                        mayContain = new HashSet<>();
                        ingredientsMayContain.put(ingredient, mayContain);
                    }
                    // Add all the allergens to this ingredient's set
                    mayContain.addAll(allergens);
                }
                // Also build up our collection of allergens and which ingredients MAY contain them
                for (String allergen : allergens) {
                    HashSet<String> containedBy;
                    if (allergensToIngredientsMap.containsKey(allergen)) {
                        // If we've seen this ingredient before
                        containedBy = allergensToIngredientsMap.get(allergen);
                    } else {
                        // New ingredient, make a new (empty) allergen set
                        containedBy = new HashSet<>();
                        allergensToIngredientsMap.put(allergen, containedBy);
                    }
                    // Add all the allergens to this ingredient's set
                    containedBy.addAll(ingredients);
                }
                // Finally, create a bunch of Foods that replicate all those ingredient lines
                foods.put(i, new Food(i, ingredients, allergens));
                i++;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
