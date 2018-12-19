import java.util.Scanner;
import java.lang.Math;

class gasuse {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter your start odometer: ");
    int odometer1 = in.nextInt();
    System.out.println("Enter your end odometer: ");
    int odometer2 = in.nextInt();
    System.out.println("Enter your start fuel: ");
    int fuel1 = in.nextInt();
    System.out.println("Enter your end fuel: ");
    int fuel2 = in.nextInt();
    odometer2 -= odometer1;
    fuel1 -= fuel2;
    double mpg = odometer2 / fuel2;
    System.out.println("Miles per gallon is: " + mpg);
  }
}
