import java.util.Scanner;

public class Calculator {

    public void calculate() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter operator (+, -, *, /): ");
        char operator = sc.next().charAt(0);

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error! Division by zero.");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operator!");
                return;
        }

        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;

        while (cont) {
            Calculator calc = new Calculator();
            calc.calculate();

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String ans = sc.next().toLowerCase();

            if (ans.equals("yes")) {
                cont = true;
            } else {
                cont = false;
                System.out.println("Goodbye!");
            }
        }

        sc.close();
    }
}
