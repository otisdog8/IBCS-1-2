import java.util.Scanner;
import java.lang.Math;

class numinput {
  public static void main(String args[]) {
    //Gets inputs
    Scanner in = new Scanner(System.in);
    System.out.println("Enter your first number: ");
    double firstnum = in.nextFloat();
    System.out.println("Enter your second number: ");
    double secondnum = in.nextFloat();
    //Calculation and result output
    System.out.println("Sum is: " + (firstnum + secondnum));
    System.out.println("Difference is: " + (Math.abs(firstnum - secondnum)));
    System.out.println("Product is: " + (firstnum * secondnum));
    System.out.println("Quotient is: " + (firstnum / secondnum));
  }
}
