import java.util.Scanner;
import java.lang.Math;

class gasuse {
  public static void main(String args[]) {
    //Gets odometer and fuel data
    Scanner in = new Scanner(System.in);
    System.out.println("Enter your start odometer: ");
    double distancestart = in.nextFloat();
    System.out.println("Enter your end odometer: ");
    double distancefinish = in.nextFloat();
    System.out.println("Enter your start fuel: ");
    double fuelstart = in.nextFloat();
    System.out.println("Enter your end fuel: ");
    double fuelfinish = in.nextFloat();
    //Calculates the miles per gallon
    double distancetraveled = distancefinish - distancestart;
    double fuelused = fuelstart - fuelfinish;
    double mpg = distancetraveled / fuelused;
    //Displays the result
    System.out.println("Miles per gallon is: " + mpg);
  }
}
