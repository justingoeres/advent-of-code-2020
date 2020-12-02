package org.jgoeres.adventofcode2020.Day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Password {
    int minOccurrences;
    int maxOccurrences;
    char requiredLetter;
    int firstPosition;  // for Part B
    int secondPosition; // for Part B
    String password;

    public Password(int minOccurrences, int maxOccurrences, char requiredLetter, String password) {
        this.minOccurrences = minOccurrences;
        this.maxOccurrences = maxOccurrences;
        this.requiredLetter = requiredLetter;
        this.password = password;

        // for Part B
        this.setFirstPosition(this.getMinOccurrences());
        this.setSecondPosition(this.getMaxOccurrences());
    }

    public Password(String encodedPassword) {
        // PART A
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

        // PART B
        // Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) Exactly one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
        //
        // Given the same example list from above:
        //
        // 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
        // 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
        // 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.

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

            // for Part B
            this.setFirstPosition(this.getMinOccurrences() - 1);    // indexing starts at 1 per problem description
            this.setSecondPosition(this.getMaxOccurrences() - 1);
        } else {
            System.out.println("NO MATCH");
        }
    }

    public boolean isValidPartA() {
        // Return TRUE if this password is valid according to Part A
        // Turn the password into a stream of characters
        Stream<Character> characterStream = getPassword().chars().mapToObj(c -> (char) c);

        // Count the instances of the required letter
        long count = characterStream.filter(c -> c == getRequiredLetter()).count();

        boolean valid = (count >= minOccurrences && count <= maxOccurrences);

        return valid;
    }

    public boolean isValidPartB() {
        // Return TRUE if this password is valid according to Part B
        boolean firstMatch = false;
        boolean secondMatch = false;

        firstMatch = getPassword().charAt(getFirstPosition()) == getRequiredLetter();
        secondMatch = getPassword().charAt(getSecondPosition()) == getRequiredLetter();

        // EXACTLY ONE of the matches must be true.
        boolean valid = firstMatch ^ secondMatch;

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

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(int secondPosition) {
        this.secondPosition = secondPosition;
    }
}
