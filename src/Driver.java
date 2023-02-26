import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("Please select an option:");
            System.out.println("1. Postfix conversion");
            System.out.println("2. Postfix evaluation");
            System.out.println("3. Quit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Please enter an infix expression:");
                    String inExpression = scanner.nextLine();

                    if (PostfixConversion.isValidInfixExpression(inExpression)) {
                        String postfixExpression = PostfixConversion.convertToPostfix(inExpression);
                        System.out.println("The corresponding postfix expression is:");
                        System.out.println(postfixExpression);
                    } else {
                        System.out.println("Invalid infix expression");
                    }

                    break;

                case 2:
                    System.out.println("Please enter a postfix expression:");
                    String postfixExpression = scanner.nextLine();

                    if (PostfixEvaluation.isValidPostfixExpression(postfixExpression)) {
                        int result = PostfixEvaluation.evaluatePostfixExpression(postfixExpression);
                        System.out.println("The result of the postfix expression is:");
                        System.out.println(result);
                    } else {
                        System.out.println("Invalid postfix expression");
                    }

                    break;

                case 3:
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
