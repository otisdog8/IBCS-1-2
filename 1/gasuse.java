import java.util.Scanner;
import java.lang.Math;

class gasuse {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter your start odometer: ");
    float odometer1 = in.nextFloat();
    System.out.println("Enter your end odometer: ");
    float odometer2 = in.nextFloat();
    System.out.println("Enter your start fuel: ");
    float fuel1 = in.nextFloat();
    System.out.println("Enter your end fuel: ");
    float fuel2 = in.nextFloat();
    odometer2 -= odometer1;
    fuel1 -= fuel2;
    float mpg = (float) odometer2 / (float) fuel1;
    System.out.println("Miles per gallon is: " + mpg);
  }
}
