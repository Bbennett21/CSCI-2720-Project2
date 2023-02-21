public class PostfixConversion {
    public static boolean isValidInfixExpression(String infixExpression) {
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                } // if
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                } // if
            } else if (Character.isDigit(c)) {
                // Makes sure a digit is followed by an operator or closing bracket
                if (i < infixExpression.length() - 1) {
                    char next = infixExpression.charAt(i + 1);
                    if (Character.isDigit(next)) {
                        return false;
                    } // if
                } // if
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // If current character is an operator, make sure it is surrounded by digits
                if (i == 0 || i == infixExpression.length() - 1) {
                    return false;
                } // if
                char prev = infixExpression.charAt(i - 1);
                char next = infixExpression.charAt(i + 1);
                if (Character.isDigit(prev) && Character.isDigit(next)) {
                    continue;
                } else {
                    return false;
                } // if
            } else {
                return false; // Invalid character found
            } // if
        } // for
        return stack.isEmpty();
    } // isValidInfixExpression

    public static String convertToPostfix(String infix) throws IllegalArgumentException {
        LinkedListStack stack = new LinkedListStack();
        String postfix = "";
        for (char c : infix.toCharArray()) {
            if (c == ' ') {
                continue;
            } // if
            if (Character.isLetterOrDigit(c)) {
                postfix += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                } // while
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Unbalanced parentheses.");
                } // if
                stack.pop(); // Pop the left parenthesis
            } else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    postfix += stack.pop();
                } // while
                stack.push(c);
            } // if
        } // for
        while (!stack.isEmpty()) {
            if (stack.peek() == '(' || stack.peek() == ')') {
                throw new IllegalArgumentException("Unbalanced parentheses.");
            } // if
            postfix += stack.pop();
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
