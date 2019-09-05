import java.util.Scanner;

public class fiveints {

  public static void main(String[] args) {
    //Get input
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a five digit integer"); //Note: This works for ints of any length
    int fivenums = input.nextInt();
    //Converts the ints into a string to make operations easier
    String fivenumsstr = Integer.toString(fivenums);
    String result = "";
    for (int i = 0; i < fivenumsstr.length(); i++) {
      //Iterates through fivenumsstr and adds three spaces if it's not the last character
      char c = fivenumsstr.charAt(i);
      if (i + 1 == fivenumsstr.length()) {
          result = result + c;
          //Makes it so there is no space at the end
      } else {
          result = result + c + "   ";
      }
    }
    //Displays result
    System.out.println(result);
  }
}
