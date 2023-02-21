public class PostfixEvaluation {
    public static boolean isValidPostfixExpression(String postfixExpression) {
        // TODO: Implement code to verify validity of postfix expression
        return true;
    }

    public static int evaluatePostfix(String postfixExpression) {
        if (!isValidPostfixExpression(postfixExpression)) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }

        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for (int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);

            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;

                    case '-':
                        stack.push(operand1 - operand2);
                        break;

                    case '*':
                        stack.push(operand1 * operand2);
                        break;

                    case '/':
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
