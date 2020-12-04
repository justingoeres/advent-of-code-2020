package org.jgoeres.adventofcode2020.Day04;

import java.util.HashMap;
import java.util.HashSet;

public class Passport {
    HashMap<String, String> fields = new HashMap<>();
    HashSet<String> requiredFields = new HashSet<>();

    public Passport() {
    }

    public void addRequiredField(String requiredField) {
        requiredFields.add(requiredField);
    }

    public void setFieldValue(String fieldName, String value) {
        // Add the named field to the Map, or replace its value if it already exists
        fields.put(fieldName, value);
    }

    public boolean isValid() {
        // Check to make sure all the required fields are present in 'fields'
        boolean isValid = requiredFields.stream().allMatch(f -> fields.containsKey(f));
        return isValid;
    }

    public boolean isValid(HashSet<String> fieldsToCheck) {
        // Check to make sure all the given fields are present in 'fields'
        boolean isValid = fieldsToCheck.stream().allMatch(f -> fields.containsKey(f));
        return isValid;
    }
}
