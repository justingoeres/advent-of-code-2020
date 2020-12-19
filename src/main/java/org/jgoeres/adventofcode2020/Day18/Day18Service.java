package org.jgoeres.adventofcode2020.Day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.jgoeres.adventofcode2020.Day18.Op.*;

public class Day18Service {
    private final String DEFAULT_INPUTS_PATH = "data/day18/input.txt";

    private static boolean DEBUG = false;
    private ArrayList<String> inputList = new ArrayList<>();


    private static final char OPEN_CHAR = '(';
    private static final char CLOSE_CHAR = ')';

    private static final String ADD_STR = "+";
    private static final String MULTIPLY_STR = "*";
    private static final String OPEN_STR = "(";
    private static final String CLOSE_STR = ")";
    private static final String SPACE_STR = " ";

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
            if (DEBUG) {
                System.out.println(value);
            }
            sum = sum.add(value);
        }
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
            if (DEBUG) {
                System.out.println(expression);
            }
            BigInteger value = evaluatePartB(expression);
            if (DEBUG) {
                System.out.println("\t\t= " + value);
            }
            sum = sum.add(value);
        }
        return sum;
    }

    public BigInteger evaluatePartB(String expression) {
        // Process the expression by finding subexpressions enclosed by ( ) and evaluating those.
        // Each time we process one, REPLACE it in the expression with the subexpression result, and then start over.
        // When we run out of parentheses, then do a final evaluation.
        BigInteger result;
        Character c;
        int pBegin = 0;
        int pEnd = 0;
        while (true) {
            boolean found = false;
            for (int i = 0; i < expression.length(); i++) {
                c = expression.charAt(i);
                // Go until we find a CLOSE
                if (c == OPEN_CHAR) {
                    // Every time we find an OPEN, note it
                    pBegin = i;
                    found = true;
                } else if (c == CLOSE_CHAR) {
                    pEnd = i;
                    break; // When we find a set of (...), process it
                }
            }
            if (found) {
                // If we found a set of (...)
                // Evaluate the stuff inside it
                String subexpression = expression.substring(pBegin, pEnd + 1);
                BigInteger subExpressionResult = evaluate(subexpression);
                // Now that we have a result, REPLACE the parens with the result.
                expression = expression.replace(subexpression, subExpressionResult.toString());
                // And iterate to scan the new simplified expression
            } else {
                // No parens left, evaluate the remaining bits to get the final result
                result = evaluate(expression);
                break;  // and we're done!
            }
        }
        return result;
    }

    public BigInteger evaluate(String expression) {
        Stack<BigInteger> argStack = new Stack<>();
        Stack<Op> opStack = new Stack<>();

        BigInteger arg = BigInteger.ZERO;
        BigInteger arg2;

        expression = expression.replace("(", "").replace(")", "");
        List<String> splitExpression = Arrays.asList(expression.split(" "));
        for (String element : splitExpression) {
            if ((element.equals(SPACE_STR)) || (element.equals(OPEN_STR)) || (element.equals(CLOSE_STR))) {
                // skip spaces & parens
                continue;
            } else if (element.equals(ADD_STR)) {
                opStack.push(ADD);
                continue;
            } else if (element.equals(MULTIPLY_STR)) {
                // Put the current (multiply) operation on the stack to process later
                opStack.push(MULTIPLY);
                continue;
            } else {
                // It's a number; parse it
                arg = BigInteger.valueOf(Long.parseLong(element));
            }
            if (arg != null) {
                // Peek at the top of the stack. If it's an ADD, process it immediately
                if (!opStack.isEmpty() && opStack.peek() == ADD) {
                    // Pop both stacks; store the argStack element for immediate use
                    opStack.pop();  // Get rid of the ADD
                    arg2 = argStack.pop();
                    arg = arg.add(arg2);    // ADD them
                }
                // store the result back on the stack, or put the new number there
                argStack.push(arg);
            }
        }
        if (!argStack.isEmpty()) {
            while (!opStack.isEmpty()) {
                Op op = opStack.pop();
                arg = argStack.pop(); // get the first argument
                arg2 = argStack.pop();
                switch (op) {
                    case ADD:
                        arg = arg.add(arg2);
                        argStack.push(arg);
                        break;
                    case MULTIPLY:
                        arg = arg.multiply(arg2);
                        argStack.push(arg);
                        break;
                }
            }
        }
        // The final result of the (sub)expression will be in arg. Return it.
        return arg;
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
