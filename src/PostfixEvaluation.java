import java.util.Stack;

public class PostfixEvaluation {
    public static boolean isValidPostfixExpression(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } else if (isOperator(c)) {
                if (stack.size() < 2) {
                    return false;
                }
                stack.pop();
                stack.pop();
                stack.push(0); // placeholder value
            } else {
                // invalid character
                return false;
            }
        }
        return stack.size() == 1;
    }

    public static int evaluatePostfixExpression(String postfix) {
        if (!isValidPostfixExpression(postfix)) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
        Stack<Integer> stack = new Stack<>();
        for (char c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } else if (isOperator(c)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = applyOperator(c, operand1, operand2);
                stack.push(result);
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int applyOperator(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
