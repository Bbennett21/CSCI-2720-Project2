public class PostfixConversion {
    public static boolean isValidInfixExpression(String infixExpression) {
    LinkedListStack stack = new LinkedListStack();
    boolean prevWasDigit = false; // flag to keep track of whether previous character was a digit
    for (int i = 0; i < infixExpression.length(); i++) {
        char c = infixExpression.charAt(i);
        if (c == ' ') {
            continue; // skip whitespace
        } else if (c == '(') {
            stack.push(c);
            prevWasDigit = false; // reset flag
        } else if (c == ')') {
            if (stack.isEmpty()) {
                return false;
            } // if
            char top = stack.pop();
            if (c == ')' && top != '(') {
                return false;
            } // if
            prevWasDigit = false; // reset flag
        } else if (Character.isDigit(c)) {
            // Makes sure a digit is part of a single number
            if (prevWasDigit) {
                continue; // digit is part of same number
            } // if
            int j = i + 1;
            while (j < infixExpression.length() && Character.isWhitespace(infixExpression.charAt(j))) {
                j++; // skip whitespace
            } // while
            if (j < infixExpression.length() && Character.isDigit(infixExpression.charAt(j))) {
                prevWasDigit = true; // digit is part of same number
            } else {
                prevWasDigit = false; // digit is not part of same number
            } // if
        } else if (c == '+' || c == '-' || c == '*' || c == '/') {
            // If current character is an operator, make sure it is surrounded by digits
            if (i == 0 || i == infixExpression.length() - 1) {
                return false;
            } // if
            int j = i - 1;
            while (j >= 0 && Character.isWhitespace(infixExpression.charAt(j))) {
                j--; // skip whitespace
            } // while
            if (j < 0 || !Character.isDigit(infixExpression.charAt(j))) {
                return false;
            } // if
            j = i + 1;
            while (j < infixExpression.length() && Character.isWhitespace(infixExpression.charAt(j))) {
                j++; // skip whitespace
            } // while
            if (j >= infixExpression.length() || !Character.isDigit(infixExpression.charAt(j))) {
                return false;
            } // if
            prevWasDigit = false; // reset flag
        } else {
            return false; // Invalid character found
        } // if
    } // for
    return stack.isEmpty();
} // isValidInfixExpression


    public static String convertToPostfix(String infix) throws IllegalArgumentException {
        LinkedListStack stack = new LinkedListStack();
        String postfix = "";
        String number = "";
        for (char c : infix.toCharArray()) {
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                number += c;
            } else if (Character.isLetter(c)) {
                postfix += c;
                postfix += " ";
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!number.equals("")) {
                    postfix += number;
                    postfix += " ";
                    number = "";
                }
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                    postfix += " ";
                } // while
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Unbalanced parentheses.");
                } // if
                stack.pop(); // Pop the left parenthesis
            } else {
                if (!number.equals("")) {
                    postfix += number;
                    postfix += " ";
                    number = "";
                }
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    postfix += stack.pop();
                    postfix += " ";
                } // while
                stack.push(c);
            } // if
        } // for
        if (!number.equals("")) {
            postfix += number;
            postfix += " ";
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(' || stack.peek() == ')') {
                throw new IllegalArgumentException("Unbalanced parentheses.");
            } // if
            postfix += stack.pop();
            postfix += " ";
        } // while
        return postfix;
    } // convertToPostfix


    private static int getPrecedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else {
            return -1;
        } // if
    } // precedence

} // PostfixConversion
