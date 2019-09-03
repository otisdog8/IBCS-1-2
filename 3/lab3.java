package com.company;
import java.util.Scanner;

public class lab3 {
    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
    public static void main(String[] args) {
        int response;
	    String[] items = {"End Program","Quadratic","Pythagorean"}; //Menu code
	    String menu = makemenu(items);
        do {
            System.out.println(menu);
	        response = input.nextInt();
            if (response == 1) {
                quadratic(); //Runs the specified program
            } else if (response == 2) {
                pythagorean();
            } else {

            }

        } while (response != 0);
        System.out.println("Bye!");
    }
    public static String makemenu(String[] items) { //Function generates a menu string so that I don't have to type it out.
        String result = "";
        int length = items.length; //*very* slight optimization
        for(int i = 0;i < length; i++) { //Iterates through the array items and generates a menu entry for each item on items
            result += i + ":  " + items[i] + "\n";
        }
        return result;
    }
    public static void quadratic() {
        System.out.println("Enter your \"A\" Value"); //Gets values
        double aval = input.nextDouble();
        System.out.println("Enter your \"B\" Value");
        double bval = input.nextDouble();
        System.out.println("Enter your \"C\" Value");
        double cval = input.nextDouble();
        double discriminant = Math.pow(bval,2)-(4*aval*cval); //calculates the discriminant so we can use it as a computational shortcut
        double minusbover2a = bval * -1 / 2 / aval;
        double root0 = 0; //value for the roots
        double root1 = 0;
        if (discriminant > 0) {
            root0 = minusbover2a + Math.sqrt(discriminant)/2/aval; //Calculating both real roots
            root1 = minusbover2a - Math.sqrt(discriminant)/2/aval;
            System.out.println("The equation has real roots");
            System.out.println("The roots are: " + root0 + " and " + root1);
        } else if (discriminant == 0) {
            System.out.println("The equation has one root");
            System.out.println("The root is 0"); //Don't need to calculate roots; if the discriminant is 0 there is only a root with 0
        } else if (discriminant < 0) {
            String root0str = "(" + minusbover2a + " + " + (Math.sqrt(discriminant*-1)/2/aval) + "i)"; //Parses imaginary roots into imaginary form.
            String root1str = "(" + minusbover2a + " - " + (Math.sqrt(discriminant*-1)/2/aval) + "i)";
            System.out.println("The equation has no real roots");
            System.out.println("The imaginary roots are " + root0str + " and " + root1str);
        } else {

        }
    }
    public static void pythagorean() {
        System.out.println("For the unknown, put -1");
        System.out.println("Enter your \"A\" Value"); //Gets values
        double aval = input.nextDouble();
        System.out.println("Enter your \"B\" Value");
        double bval = input.nextDouble();
        System.out.println("Enter your \"C\" Value");
        double cval = input.nextDouble();
        double result;
        int display = 0;
        if (aval == -1) {
            result = Math.sqrt(Math.pow(cval,2)-Math.pow(bval,2)); //Solving calculations
            aval = result;
        } else if (bval == -1) {
            result = Math.sqrt(Math.pow(cval,2)-Math.pow(aval,2)); //Solving calculations
            bval = result;
        } else if (cval == -1) {
            result = Math.sqrt(Math.pow(aval,2)+Math.pow(bval,2)); //Solving calculations
            cval = result;
        } else {
            result = 0;
            System.out.println("You didn't put -1. Try again.");
            display = 1;
        }
        if (display == 0) {
            System.out.println("Value of the missing side is: " + result);
            System.out.println("Angle A is; " + (Math.atan(aval / bval)*180/Math.PI)); //Calculates angle than converts from radians to degrees
            System.out.println("Angle B is; " + (Math.atan(bval / aval)*180/Math.PI)); //Calculates angle than converts from radians to degrees
            System.out.println("Angle C is: 90"); //Since this is a right triangle this is given
        }
    }

}
