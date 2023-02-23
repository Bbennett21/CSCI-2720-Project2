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
                int j = i + 1;
                while (j < infixExpression.length() && Character.isWhitespace(infixExpression.charAt(j))) {
                    j++; // skip whitespace
                } // while
                if (j < infixExpression.length() - 1) {
                    char next = infixExpression.charAt(j + 1);
                    if (Character.isDigit(next)) {
                        return false;
                    } // if
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
            } else if (c == ' ') {
                continue;
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
                postfix += " ";
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                    postfix += " ";
                } // while
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Unbalanced parentheses.");
                } // if
                stack.pop(); // Pop the left parenthesis
            } else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    postfix += stack.pop();
                    postfix += " ";
                } // while
                stack.push(c);
            } // if
        } // for
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
