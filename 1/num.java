//Author: Mr. Tinling
//Class Computer Science 1/2 Period 2 or 3
//Last Edited: Aug 21, 2018
import java.io.*;
import java.util.Random;
import java.util.Scanner;

//This program does some very simple things. It declares a couple of variables and initializes
//them. It then adds them together and prints out the sum. After this it inputs a number from the
//keyboard and adds it to the total.
class num {
public static void main(String args[]) {
int firstnumber = 5;
int secondnumber = 17;
int usernumber = 0;
int sum = 0;
Scanner in = new Scanner(System.in);
System.out.println("Welcome to my number adding machine");
sum = firstnumber + secondnumber;
System.out.println("The sum of " + firstnumber + " and " + secondnumber + " is " + sum);

System.out.println("Please enter a number");
usernumber = in.nextInt();
sum+= usernumber;
System.out.println("The sum of the numbers is " + sum);
} //main
}//num
