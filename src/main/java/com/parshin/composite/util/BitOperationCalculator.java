package com.parshin.composite.util;

import java.util.*;

public class BitOperationCalculator {
    private static final BitOperationCalculator instance = new BitOperationCalculator();

    private BitOperationCalculator() {

    }

    public static BitOperationCalculator getInstance(){
        return instance;
    }

    public String calculateBitExpression(String expression) {
        List<String> rpnExpression = expressionToRpn(expression);
        return String.valueOf(calculateRpn(rpnExpression));
    }

    private List<String> expressionToRpn(String expression) {  // Expression -> ~6&9|(3&4)
        Deque<String> stack = new ArrayDeque<>();
        List<String> tokenList = new ArrayList<>();

        StringBuilder token;
        for (int i = 0; i < expression.length(); i++) {
            token = new StringBuilder();
            token.append(expression.charAt(i));

            if (Character.isDigit(expression.charAt(i))) {
                while (i < expression.length() - 1 && Character.isDigit(expression.charAt(i + 1))) {
                    token.append(expression.charAt(++i)); //adds full number ass one token
                }
            } else {
                while (i < expression.length() - 1 && token.charAt(0) != ')' && token.charAt(0) != '(' && token.charAt(0) == expression.charAt(i + 1)) {  //the same mark?
                    token.append(expression.charAt(++i)); //adds <<, >> and >>> as one token
                }
            }

            int operatorPriority = getOperatorPriority(token.toString());
            switch (operatorPriority) {
                case (-1) -> { // adds all bitwise operators from stack to tokens' list when finds closing bracket
                    while (getOperatorPriority(stack.peek()) != 1) {
                        tokenList.add(stack.pop());
                    }
                    stack.pop();
                }
                case (0) -> tokenList.add(token.toString()); // adds number to list of tokens
                case (1) -> stack.push(token.toString()); // adds opening bracket to stack
                case (2), (3), (4), (5), (6) -> { // adds bitwise operators to tokens' list and stack in right order
                    while (!stack.isEmpty()) {
                        if (getOperatorPriority(stack.peek()) >= operatorPriority) {
                            tokenList.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(token.toString());
                }
            }
        }
        while (!stack.isEmpty()) { // adds all remaining operators to tokens' list after end of the loop
            tokenList.add(stack.pop());
        }
        return tokenList;
    }

    private int calculateRpn(List<String> tokenList) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String s : tokenList) {

            if (getOperatorPriority(s) == 0) {
                stack.push(Integer.parseInt(s));
            }
            if (getOperatorPriority(s) > 1) {
                Integer b = stack.pop();
                if (s.equals("~")) {
                    stack.push(~b);
                } else {
                    Integer a = stack.pop();
                    switch (s) {
                        case ("<<") -> stack.push(a << b);
                        case (">>") -> stack.push(a >> b);
                        case (">>>") -> stack.push(a >>> b);
                        case ("&") -> stack.push(a & b);
                        case ("|") -> stack.push(a | b);
                        case ("^") -> stack.push(a ^ b);
                    }
                }
            }
        }
        return stack.pop();
    }

    private int getOperatorPriority(String token) {
        return switch (token) {
            case ("|") -> 2;
            case ("^") -> 3;
            case ("&") -> 4;
            case (">>>"), (">>"), ("<<") -> 5;
            case ("~") -> 6;
            case ("(") -> 1;
            case (")") -> -1;
            default -> 0;
        };
    }
}
