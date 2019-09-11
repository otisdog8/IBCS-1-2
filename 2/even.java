import java.util.Scanner;

//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

public class even {

  public static void main(String[] args) {
    //Get input
    Scanner input = new Scanner(System.in);
    System.out.println("Enter an integer");
    int number = input.nextInt();
    //Return result
    if (number % 2 == 0) {
      System.out.println("Your number is even");
    } else if (number % 2 == 1){
      System.out.println("Your number is odd");
    } else {
      System.out.println("ERROR");
    }
  }
}
