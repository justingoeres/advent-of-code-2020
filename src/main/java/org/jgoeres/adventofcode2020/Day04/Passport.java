package org.jgoeres.adventofcode2020.Day04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
    private final HashMap<String, String> fields = new HashMap<>();

    // hgt (Height) - a number followed by either cm or in:
    //  If cm, the number must be at least 150 and at most 193.
    //  If in, the number must be at least 59 and at most 76.
    private final Pattern hgtPattern = Pattern.compile("(\\d+)(cm|in)");
    // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    private final Pattern hclPattern = Pattern.compile("^#[0-9a-fA-F]{6}$");
    // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    private final Pattern eclPattern = Pattern.compile("^amb|blu|brn|gry|grn|hzl|oth$");
    // pid (Passport ID) - a nine-digit number, including leading zeroes.
    private final Pattern pidPattern = Pattern.compile("^\\d{9}$");

    public Passport() {
    }

    public void setFieldValue(String fieldName, String value) {
        // Add the named field to the Map, or replace its value if it already exists
        fields.put(fieldName, value);
    }

    public boolean isValidPartA(HashSet<String> fieldsToCheck) {
        // Check to make sure all the given fields are present in 'fields'
        boolean isValid = fieldsToCheck.stream().allMatch(f -> fields.containsKey(f));
        return isValid;
    }

    public boolean isValidPartB(HashSet<String> fieldsToCheck) {
        // Check to make sure all the given fields are present in 'fields'
        boolean isValid = fieldsToCheck.stream().allMatch(f -> fields.containsKey(f) && fieldIsValid(f));
        return isValid;
    }

    private boolean fieldIsValid(String fieldName) {
        String fieldValue = fields.get(fieldName);
        boolean isValid = false;
        switch (fieldName) {
            case "byr":
                // byr (Birth Year) - four digits; at least 1920 and at most 2002.
                Integer byr = Integer.parseInt(fieldValue);
                isValid = byr >= 1920 && byr <= 2002;
                break;
            case "iyr":
                // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
                Integer iyr = Integer.parseInt(fieldValue);
                isValid = iyr >= 2010 && iyr <= 2020;
                break;
            case "eyr":
                // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                Integer eyr = Integer.parseInt(fieldValue);
                isValid = eyr >= 2020 && eyr <= 2030;
                break;
            case "hgt":
                // hgt (Height) - a number followed by either cm or in:
                //  If cm, the number must be at least 150 and at most 193.
                //  If in, the number must be at least 59 and at most 76.
                Matcher hgtMatcher = hgtPattern.matcher(fieldValue);
                if (hgtMatcher.find()) {
                    Integer height = Integer.parseInt(hgtMatcher.group(1));
                    String unit = hgtMatcher.group(2);
                    switch (unit) {
                        case "cm":
                            isValid = height >= 150 && height <= 193;
                            break;
                        case "in":
                            isValid = height >= 59 && height <= 76;
                            break;
                    }
                }
                break;
            case "hcl":
                // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                Matcher hclMatcher = hclPattern.matcher(fieldValue);
                isValid = hclMatcher.find();
                break;
            case "ecl":
                // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
                Matcher eclMatcher = eclPattern.matcher(fieldValue);
                isValid = eclMatcher.find();
                break;
            case "pid":
                // pid (Passport ID) - a nine-digit number, including leading zeroes.
                Matcher pidMatcher = pidPattern.matcher(fieldValue);
                isValid = pidMatcher.find();
                break;
            // cid (Country ID) - ignored, missing or not.
        }
        return isValid;
    }
}
