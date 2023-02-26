public class PostfixConversion {

    public static boolean isValidInfixExpression(String infixExpression) {
    LinkedListStack stack = new LinkedListStack();
    boolean prevWasDigit = false;

    for (int i = 0; i < infixExpression.length(); i++) {
        char c = infixExpression.charAt(i);

        if (c == ' ') {
            continue;
        } else if (c == '(') {
            stack.push(c);
            prevWasDigit = false;
        } else if (c == ')') {
            if (stack.isEmpty()) {
                return false;
            }
            char top = (char) stack.pop();
            if (c == ')' && top != '(') {
                return false;
            }
            prevWasDigit = false;
        } else if (Character.isDigit(c)) {
            if (prevWasDigit) {
                continue;
            }
            int j = i + 1;
            while (j < infixExpression.length() &&
                   Character.isWhitespace(infixExpression.charAt(j))) {
                j++;
            }
            if (j < infixExpression.length() && Character.isDigit(infixExpression.charAt(j))) {
                prevWasDigit = true;
            } else {
                prevWasDigit = false;
            }
        } else if (c == '+' || c == '-' || c == '*' || c == '/') {
            if (i == 0 || i == infixExpression.length() - 1) {
                return false;
            }
            int j = i - 1;
            while (j >= 0 && Character.isWhitespace(infixExpression.charAt(j))) {
                j--;
            }
            if (j < 0 || !Character.isDigit(infixExpression.charAt(j)) && infixExpression.charAt(j) != ')') {
                return false;
            }
            j = i + 1;
            while (j < infixExpression.length() && Character.isWhitespace(infixExpression.charAt(j))) {
                j++;
            }
            if (j >= infixExpression.length() || !Character.isDigit(infixExpression.charAt(j)) && infixExpression.charAt(j) != '(') {
                return false;
            }
            prevWasDigit = false;
        } else {
            return false;
        }
    }

    return stack.isEmpty();
}



    public static String convertToPostfix(String infix) throws IllegalArgumentException {
    LinkedListStack<Character> stack = new LinkedListStack<>();
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
    return postfix.trim();
} // convertToPostfix
    private static int getPrecedence(char c) {
    if (c == '+' || c == '-') {
        return 1;
    } else if (c == '*' || c == '/') {
        return 2;
    } else if (c == '^') {
        return 3;
    } else {
        return -1;
    }
}

}
