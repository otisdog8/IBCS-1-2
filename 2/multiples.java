import java.util.Scanner;

//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

public class multiples {

  public static void main(String[] args) {
    //Gets input
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your first number");
    int firstnum = input.nextInt();
    System.out.println("Enter your second number");
    int secondnum = input.nextInt();
    //Gets modulus and returns result
    if (firstnum % secondnum == 0) {
      System.out.println("The first number is a multiple of the second.");
    } else {
      System.out.println("The first number isn't a multiple of the second.");
    }
  }
}
