package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
        System.out.println("Enter an integer");
        int number = input.nextInt();
        if (number % 2 == 0) {
            System.out.println("Your number is even");
        } else if (number % 2 == 1){
            System.out.println("Your number is odd");
        } else {
            System.out.println("ERROR");
        }
    }
}
