import java.util.Scanner;
class circumference {
  public static void main( String args[] ) {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the radius:  ");
    double radius = in.nextFloat();
    radius = radius * 3.14159;
    System.out.println("The circumference is:  " + radius);
  }
}
