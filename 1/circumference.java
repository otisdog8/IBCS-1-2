import java.util.Scanner;
class circumference {
  public static void main( String args[] ) {
    //Get Radius
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the radius:  ");
    double radius = in.nextFloat();
    //Calculation
    radius = 2 * radius * 3.14159;
    //Result
    System.out.println("The circumference is:  " + radius);
  }
}
