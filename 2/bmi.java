package com.company;
import java.util.Scanner;

public class bmi {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your weight in pounds");
        double weight = input.nextDouble();
        System.out.println("Enter your height in inches");
        double height = input.nextDouble();
        double BMI = weight * 703 / Math.pow(height, 2);
        BMI = Math.round(BMI * 100.0) / 100.0;
        String result;
        if (BMI > 30) {
            result = "obese";
        } else if (BMI <= 30 && BMI > 25) {
            result = "overweight";
        } else if (BMI <= 25 && BMI >= 18.5) {
            result = "normal";
        } else if (BMI < 18.5) {
            result = "underweight";
        } else {
            result = "ERROR";
        }
        System.out.println("Your BMI is " + BMI + " and you're " + result + ".");
    }
}
