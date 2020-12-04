package org.jgoeres.adventofcode2020.Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportService {
    private final String DEFAULT_INPUTS_PATH = "data/day04/input.txt";
    private static final String EMPTY = "";
    private static boolean DEBUG = false;

    private ArrayList<Passport> passports = new ArrayList<>();
    private HashSet<String> requiredFields = new HashSet<>();

    public PassportService() {
        loadInputs(DEFAULT_INPUTS_PATH);
        requiredFields.add("byr");
        requiredFields.add("iyr");
        requiredFields.add("eyr");
        requiredFields.add("hgt");
        requiredFields.add("hcl");
        requiredFields.add("ecl");
        requiredFields.add("pid");
//        requiredFields.add("cid");    // OPTIONAL
    }

    public PassportService(String pathToFile) {
        loadInputs(pathToFile);
    }

    public long doPartA() {
        /** The expected fields are as follows:
         *
         * byr (Birth Year)
         * iyr (Issue Year)
         * eyr (Expiration Year)
         * hgt (Height)
         * hcl (Hair Color)
         * ecl (Eye Color)
         * pid (Passport ID)
         * cid (Country ID)
         *
         * Count the number of valid passports - those that have all required fields.
         * Treat cid as optional.
         *
         * In your batch file, how many passports are valid?
         **/

        long validPassportCount = passports.stream().filter(p -> p.isValidPartA(requiredFields)).count();
        return validPassportCount;
    }

    public long doPartB() {
        /** You can continue to ignore the cid field, but each other field has strict rules about what values are valid for automatic validation:
         *
         * byr (Birth Year) - four digits; at least 1920 and at most 2002.
         * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
         * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
         * hgt (Height) - a number followed by either cm or in:
         * If cm, the number must be at least 150 and at most 193.
         * If in, the number must be at least 59 and at most 76.
         * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
         * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
         * pid (Passport ID) - a nine-digit number, including leading zeroes.
         * cid (Country ID) - ignored, missing or not.
         * Your job is to count the passports where all required fields are both present and valid according to the above rules.
         **/

        long validPassportCount = passports.stream().filter(p -> p.isValidPartB(requiredFields)).count();
        return validPassportCount;
    }

    // load inputs line-by-line as list of Integers 
    private void loadInputs(String pathToFile) {
        passports.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            // Example line:
            //  ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            String pattern = "(\\w{3}):(\\S+)"; // 3 letters, colon, a bunch of non-whitespace

            // Create a Pattern object
            Pattern r = Pattern.compile(pattern);
            String line;
            Passport passport = new Passport();
            // Read all the lines
            while ((line = br.readLine()) != null) {
                if (!(line.equals(EMPTY))) {
                    // If the line is NOT blank, process it
                    // Create matcher object for the current line
                    Matcher m = r.matcher(line);
                    // process all the passport fields on the line
                    while (m.find()) {
                        String fieldName = m.group(1);
                        String fieldValue = m.group(2);

                        passport.setFieldValue(fieldName, fieldValue);
                    }
                    // line finished, go to next line
                } else {
                    // else the line is blank, which means the current passport is complete
                    // Store it then continue with a new passport.
                    passports.add(passport);
                    passport = new Passport();
                }
            }
            passports.add(passport);    // Add the last passport
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

}
