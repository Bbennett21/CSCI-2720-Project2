public class PostfixConversion {
    public static boolean isValidInfixExpression(String infixExpression) {
        // TODO: Implement code to verify validity of infix expression
        return true;
    }

    public static String convertToPostfix(String infixExpression) {
        if (!isValidInfixExpression(infixExpression)) {
            throw new IllegalArgumentException("Invalid infix expression");
        }

        StringBuilder postfix = new StringBuilder();
        LinkedListStack<Character> stack = new LinkedListStack<>();

        for (int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }

                if (!stack.isEmpty() && stack.peek() != '(') {
                    throw new IllegalArgumentException("Invalid infix expression");
                }

                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }

                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new IllegalArgumentException("Invalid infix expression");
            }

            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    private static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else {
            return -1;
        }
    }
}
