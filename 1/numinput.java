import java.util.Scanner;
import java.lang.Math;

class numinput {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter your first number: ");
    float num1 = in.nextFloat();
    System.out.println("Enter your second number: ");
    float num2 = in.nextFloat();
    System.out.println("Sum is: " + (num1 + num2));
    System.out.println("Difference is: " + (Math.abs(num1 - num2)));
    System.out.println("Product is: " + (num1 * num2));
    System.out.println("Quotient is: " + (num1 / num2));
  }
}
