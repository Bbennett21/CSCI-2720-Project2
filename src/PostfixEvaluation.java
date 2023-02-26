import java.util.*;

public class PostfixEvaluation {

    public static boolean isValidPostfixExpression(String expression) {
        // check if the expression is null or empty
        if (expression == null || expression.isEmpty()) {
            return false;
        }

        // split the expression into tokens
        String[] tokens = expression.split("\\s+");

        // check if the expression has at least 2 tokens
        if (tokens.length < 2) {
            return false;
        }

        // check if all tokens are valid operands or operators
        for (String token : tokens) {
            if (!isOperand(token) && !isOperator(token)) {
                return false;
            }
        }

        // check if the expression has a valid number of operands and operators
        int numOperands = 0;
        int numOperators = 0;

        for (String token : tokens) {
            if (isOperand(token)) {
                numOperands++;
            } else if (isOperator(token)) {
                numOperators++;
            }
        }

        if (numOperands - numOperators != 1) {
            return false;
        }

        // the expression is valid
        return true;
    }

    private static boolean isOperand(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static double evaluatePostfixExpression(String expression) {
        // check if the expression is valid
        if (!isValidPostfixExpression(expression)) {
            throw new IllegalArgumentException("Invalid expression");
        }

        // split the expression into tokens
        String[] tokens = expression.split("\\s+");

        // use a stack to evaluate the expression
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperand(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }

        // the final result is at the top of the stack
        return stack.pop();
    }
}
