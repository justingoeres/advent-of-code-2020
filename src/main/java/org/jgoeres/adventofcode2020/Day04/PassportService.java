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

    public PassportService() {
        loadInputs(DEFAULT_INPUTS_PATH);
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
        HashSet<String> requiredFields = new HashSet<>();
        requiredFields.add("byr");
        requiredFields.add("iyr");
        requiredFields.add("eyr");
        requiredFields.add("hgt");
        requiredFields.add("hcl");
        requiredFields.add("ecl");
        requiredFields.add("pid");
//        requiredFields.add("cid");    // OPTIONAL

        long validPassportCount = passports.stream().filter(p -> p.isValid(requiredFields)).count();
        return validPassportCount;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
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
