package org.jgoeres.adventofcode2020.Day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
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
    AccAndOp current = new AccAndOp(BigInteger.ZERO, ADD);

    public Day18Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day18Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public BigInteger doPartA() {
        /**
         * Evaluate the expression on each line of the homework;
         * what is the sum of the resulting values? **/
        BigInteger sum = BigInteger.ZERO;
        for (String expression : inputList) {
            BigInteger value = evaluatePartA(expression);
            System.out.println(value);
            sum = sum.add(value);
        }

        System.out.println(sum);
        return sum;
    }

    public BigInteger evaluatePartA(String expression) {
        current = new AccAndOp();
        // Go left to right in the expression evaluating + and *
        // Recurse when we hit a (
        // Return when we hit a ) or the end of the line
        BigInteger arg;
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
            } else if (c == '+') {
                current.operation = ADD;
                continue;
            } else if (c == '*') {
                current.operation = Op.MULTIPLY;
                continue;
            } else {
                // It's a number; parse it
                arg = BigInteger.valueOf(Long.parseLong(c.toString()));
            }
            if (arg != null) {
                switch (current.operation) {
                    case ADD:
                        current.accumulator = current.accumulator.add(arg);
                        break;
                    case MULTIPLY:
                        current.accumulator = current.accumulator.multiply(arg);
                        break;
                }
            }
        }

        return current.accumulator;
    }


    public BigInteger doPartB() {
        /**
         * Now, addition and multiplication have different precedence levels,
         * but they're not the ones you're familiar with. Instead, addition
         * is evaluated before multiplication.
         *
         * What do you get if you add up the results of evaluating the homework
         * problems using these new rules?
         **/
        BigInteger sum = BigInteger.ZERO;
        for (String expression : inputList) {
            System.out.println(expression);
            BigInteger value = evaluatePartB(expression);
            System.out.println("\t\t= " + value);
            sum = sum.add(value);
        }
        return sum;
    }

    public BigInteger evaluatePartB(String expression) {
        current = new AccAndOp();
        // Go left to right in the expression evaluating + and *
        // Recurse when we hit a (
        // Return when we hit a ) or the end of the line
        BigInteger arg;
        for (int i = 0; i < expression.length(); i++) {
            Character c = expression.charAt(i);
            if (c == ' ') continue; // skip spaces
            else if (c == '(') {
                if (current.accumulator.compareTo(BigInteger.ZERO) == 1) {
                    // Put the current operation on the stack
                    stack.push(current);
                    // Start a new operation
                    current = new AccAndOp();
                }
                continue;
            } else if (c == ')') {
                arg = current.accumulator;
                if (!stack.isEmpty()) {
                    current = stack.pop();
                } else {
                    continue;
                }
            } else if (c == '+') {
                current.operation = ADD;
                continue;
            } else if (c == '*') {
                current.operation = Op.MULTIPLY;
                // Put the current (multiply) operation on the stack to process later
                stack.push(current);
                current = new AccAndOp();
                continue;
            } else {
                // It's a number; parse it
                arg = BigInteger.valueOf(Long.parseLong(c.toString()));
            }
            if (arg != null) {
                switch (current.operation) {
                    case ADD:
                        current.accumulator = current.accumulator.add(arg);
                        break;
                    case MULTIPLY:
                        current.accumulator = current.accumulator.multiply(arg);
                        break;
                }
            }
        }
        // When we get here do we just have multiplications on the stack??

        BigInteger total = BigInteger.ZERO;
        while (!stack.isEmpty()) {
            AccAndOp stackOp = stack.pop();
            switch (stackOp.operation) {
                case ADD:
                    current.accumulator = current.accumulator.add(stackOp.accumulator);
                    break;
                case MULTIPLY:
                    current.accumulator = current.accumulator.multiply(stackOp.accumulator);
                    break;
            }
        }
        return current.accumulator;
//        BigInteger stackResult = stack.stream().map(AccAndOp::getAccumulator)
//                .reduce(BigInteger.ONE, (a, b) -> a.multiply(b));
//        return current.accumulator.multiply(stackResult);
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
            }

        } catch (
                Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
