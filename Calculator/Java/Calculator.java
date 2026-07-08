import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simple Calculator");
        System.out.println("Enter an operation and numbers, or type 'exit' to quit.");

        while (true) {
            System.out.println();
            System.out.println("Available operations:");
            System.out.println("  +  add");
            System.out.println("  -  subtract");
            System.out.println("  *  multiply");
            System.out.println("  /  divide");
            System.out.println("  %  percentage (value of number / 100)");
            System.out.println("  sqrt  square root");
            System.out.println("  +/-   negate");
            System.out.print("Operation: ");
            String operation = scanner.next().trim().toLowerCase();

            if (operation.equals("exit") || operation.equals("quit")) {
                break;
            }

            try {
                double result;
                switch (operation) {
                    case "+":
                    case "add":
                        result = readNumber(scanner, "Enter the first number: ")
                                + readNumber(scanner, "Enter the second number: ");
                        break;
                    case "-":
                    case "subtract":
                        result = readNumber(scanner, "Enter the first number: ")
                                - readNumber(scanner, "Enter the second number: ");
                        break;
                    case "*":
                    case "multiply":
                        result = readNumber(scanner, "Enter the first number: ")
                                * readNumber(scanner, "Enter the second number: ");
                        break;
                    case "/":
                    case "divide":
                        double dividend = readNumber(scanner, "Enter the first number: ");
                        double divisor = readNumber(scanner, "Enter the second number: ");
                        if (divisor == 0) {
                            System.out.println("Error: Cannot divide by zero.");
                            continue;
                        }
                        result = dividend / divisor;
                        break;
                    case "%":
                    case "percent":
                        result = readNumber(scanner, "Enter a number: ") / 100.0;
                        break;
                    case "sqrt":
                    case "root":
                        double value = readNumber(scanner, "Enter a number: ");
                        if (value < 0) {
                            System.out.println("Error: Cannot take the square root of a negative number.");
                            continue;
                        }
                        result = Math.sqrt(value);
                        break;
                    case "+/-":
                    case "negate":
                        result = -readNumber(scanner, "Enter a number: ");
                        break;
                    default:
                        System.out.println("Unknown operation: " + operation);
                        continue;
                }
                System.out.printf("Result: %s%n", formatResult(result));
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number input. Please try again.");
                scanner.nextLine();
            }
        }

        System.out.println("Goodbye.");
        scanner.close();
    }

    private static double readNumber(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye.");
                System.exit(0);
            }
            System.out.print("Please enter a valid number: ");
        }
        return scanner.nextDouble();
    }

    private static String formatResult(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        }
        return String.format("%s", value);
    }
}
