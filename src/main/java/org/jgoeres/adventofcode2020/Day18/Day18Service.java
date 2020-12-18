package org.jgoeres.adventofcode2020.Day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

import static org.jgoeres.adventofcode2020.Day18.Op.ADD;

public class Day18Service {
    private final String DEFAULT_INPUTS_PATH = "data/day18/input.txt";

    private static boolean DEBUG = false;
    private ArrayList<String> inputList = new ArrayList<>();

    private static final char ADD_CHAR = '+';
    private static final char MULTIPLY_CHAR = '*';
    private static final char OPEN_CHAR = '(';
    private static final char CLOSE_CHAR = ')';
    private static final char SPACE = ' ';

    private Stack<AccAndOp> stack = new Stack<>();
    AccAndOp current = new AccAndOp(0, ADD);

    public Day18Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day18Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public long doPartA() {
        long result = 0;
        /**
         * Evaluate the expression on each line of the homework;
         * what is the sum of the resulting values? **/
        long sum = 0;
        for (String expression : inputList) {
            long value = evaluate(expression);
            System.out.println(value);
            sum += value;
        }

        System.out.println(sum);
        return sum;
    }

//    public long evaluate_old(String expression) {
//
//        // build the stack
//        for (int i = 0; i < expression.length(); i++) {
//            Character c = expression.charAt(i);
//            switch (c) {
//                case SPACE:
//                    // skip spaces
//                    break;
//                case ADD_CHAR:
//                    stack.push(Op.ADD.getValue());
//                    break;
//                case MULTIPLY_CHAR:
//                    stack.push(Op.MULTIPLY.getValue());
//                    break;
//                case OPEN_CHAR:
//                    stack.push(Op.OPEN.getValue());
//                    break;
//                case CLOSE_CHAR:
//                    stack.push(Op.CLOSE.getValue());
//                    break;
//                default:
//                    // It's a number!
//                    stack.push(Integer.parseInt(c.toString()));
//            }
//        }
//
//        // Process the stack
//
//        // Find all the groups of parentheses
//        ParenGroup newParenGroup = null;
//        for (int i = 0; i < expression.length(); i++) {
//            Character c = expression.charAt(i);
//            if (c == '(') {
//                // Start a new group
//            }
//        }
//        return 0;
//    }


    public long evaluate(String expression) {

        // Go left to right in the expression evaluating + and *
        // Recurse when we hit a (
        // Return when we hit a ) or the end of the line
        Long arg;
        for (int i = 0; i < expression.length(); i++) {
            Character c = expression.charAt(i);
            if (c == ' ') continue; // skip spaces
            else if (c == '(') {
                // Put the current operation on the stack
                stack.push(current);
                // Start a new operation
                current = new AccAndOp();
                continue;
            } else if (c == ')') {
                arg = current.accumulator;
                current = stack.pop();
                // TODO Pop the stack and process the operation
            } else if (c == '+') {
                current.operation = ADD;
                continue;
            } else if (c == '*') {
                current.operation = Op.MULTIPLY;
                continue;
            } else {
                // It's a number; parse it
                arg = Long.parseLong(c.toString());
            }
            if (arg != null) {
                switch (current.operation) {
                    case ADD:
                        current.accumulator += arg;
                        break;
                    case MULTIPLY:
                        current.accumulator *= arg;
                        break;
                }
            }
        }

        return current.accumulator;
    }


    public long doPartB() {
        long result = 0;
        /** Put problem implementation here **/

        return result;
    }

    public int findClosingParen(String expression, int index) {
        // Scan forward in the expression to find the closing paren that corresponds
        // to the opening one
        int i;
        for (i = index; i < expression.length(); i++) {
            // go deeper!
            Character c = expression.charAt(i);
            if (c == '(') i = findClosingParen(expression, i + 1);
            if (c == ')') return i + 1;
        }
        return i - 1; // should never get here
    }

    // load inputs line-by-line - interpret them later
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /**
             * Example:
             *   1 + (2 * 3) + (4 * (5 + 6))
             **/
//            Pattern p = Pattern.compile("([FB]{7})([LR]{3})");
            while ((line = br.readLine()) != null) {
                inputList.add(line);
                // process the line.
//                Matcher m = p.matcher(line);
//                if (m.find()) { // If our regex matched this line
//                    // Parse it
//                    String field1 = m.group(1);
//                    String field2 = m.group(2);
            }

        } catch (
                Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
