package org.jgoeres.adventofcode2020.Day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Password {
    int minOccurrences;
    int maxOccurrences;
    char requiredLetter;
    String password;

    public Password(int minOccurrences, int maxOccurrences, char requiredLetter, String password) {
        this.minOccurrences = minOccurrences;
        this.maxOccurrences = maxOccurrences;
        this.requiredLetter = requiredLetter;
        this.password = password;
    }

    public Password(String encodedPassword) {
        // For example, suppose you have the following list:
        //
        //1-3 a: abcde
        //1-3 b: cdefg
        //2-9 c: ccccccccc
        //Each line gives the password policy and then the password.
        // The password policy indicates the lowest and highest number
        // of times a given letter must appear for the password to be valid.
        //
        // For example, 1-3 a means that the password must contain 'a'
        // at least 1 time and at most 3 times.

//        String pattern = "(.*)(\\d+)(.*)";
        String pattern = "(\\d+)-(\\d+) ([a-z]): (\\w+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(encodedPassword);
        if (m.find()) {
//            System.out.println("Found value: " + m.group(1));
//            System.out.println("Found value: " + m.group(2));
//            System.out.println("Found value: " + m.group(3));
//            System.out.println("Found value: " + m.group(4));
            this.setMinOccurrences(Integer.parseInt(m.group(1)));
            this.setMaxOccurrences(Integer.parseInt(m.group(2)));
            this.setRequiredLetter(m.group(3).charAt(0));
            this.setPassword(m.group(4));
        } else {
            System.out.println("NO MATCH");
        }
        this.password = password;
    }

    public boolean isValid() {
        // Return TRUE if this password is valid

        // Turn the password into a stream of characters
        Stream<Character> characterStream = getPassword().chars().mapToObj(c -> (char) c);

        // Count the instances of the required letter
        long count = characterStream.filter(c -> c == getRequiredLetter()).count();

        boolean valid = (count >= minOccurrences && count <= maxOccurrences);

        return valid;
    }

    public int getMinOccurrences() {
        return minOccurrences;
    }

    public void setMinOccurrences(int minOccurrences) {
        this.minOccurrences = minOccurrences;
    }

    public int getMaxOccurrences() {
        return maxOccurrences;
    }

    public void setMaxOccurrences(int maxOccurrensce) {
        this.maxOccurrences = maxOccurrensce;
    }

    public char getRequiredLetter() {
        return requiredLetter;
    }

    public void setRequiredLetter(char requiredLetter) {
        this.requiredLetter = requiredLetter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
