import java.util.Scanner;

//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

public class fiveints {

  public static void main(String[] args) {
    //Get input
    Scanner input = new Scanner(System.in);
    String result = "";

    System.out.print("Enter a five digit integer:   "); //Note: This works for ints of any length
    int fivenums = input.nextInt();
    
    int length = (int) (Math.log10(fivenums) + 1); //NOTE - crashes on 0
    if (length == 0) {
      length = String.valueOf(fivenums).length() - 1; //Fallback length for anything that confuses the logarithm
    }

    for (int i = length - 1; i != -1; i--) { //Runs for each digit
      result += (int) ((fivenums - fivenums % Math.pow(10, i)) / Math.pow(10,i) % 10); //Gets the digit in the i-1th place
      result += "   ";
    }
    //Displays result
    System.out.println(result);
  }
}
