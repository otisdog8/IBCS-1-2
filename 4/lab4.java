import java.util.Scanner;
import java.util.ArrayList;
public class lab4 {
    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

    public static void main(String[] args) {
        int response;
        String[] items = {"End Program", "Add it up", "Grading", "Reciprocal", "LCM and GCD"}; //Menu code
        String menu = makemenu(items);
        do {
            System.out.println(menu);
            response = input.nextInt();
            if (response == 1) {
                additup(); //Runs the specified program
            } else if (response == 2) {
                grading();
            } else if (response == 3) {
                reciprocal();
            } else if (response == 4) {
                LCMGCD();
            } else {
            }

        } while (response != 0);
        System.out.println("Bye!");
    }

    private static String makemenu(String[] items) { //Function generates a menu string so that I don't have to type it out.
        String result = "";
        int length = items.length; //*very* slight optimization
        for (int i = 0; i < length; i++) { //Iterates through the array items and generates a menu entry for each item on items
            result += i + ":  " + items[i] + "\n";
        }
        return result;
    }

    private static void additup() {
        System.out.println("Enter an integer");
        int lastnum = input.nextInt();
        int result = 0;
        for (int i = 0; i < lastnum + 1; i++) { //Runs each time with a different i value
            result += i;
        }
        System.out.println("Your result is " + result);
    }

    private static void grading() {
    int grade;
    long[] letters = new long[] {0,0,0,0,0};
    double average = 1;
    long counter = 0; //We're using longs so that the teacher could have a very big class
    do {
        System.out.println("Enter a grade (-1 to stop)");
        grade = input.nextInt();
        if (grade >= 90) {
            letters[0]++;
        } else if (grade < 90 && grade >= 80) {
            letters[1]++;
        } else if (grade < 80 && grade >= 70) { //Calculates which letter grade the student would be given
            letters[2]++;
        } else if (grade < 70 && grade >= 60) {
            letters[3]++;
        } else if (grade < 60 && grade >= 0) {
            letters[4]++;
        } else {
        }
        if (grade != -1) { //Doesn't calculate average when exiting loop
            average = (average * counter + grade) / (counter + 1); //Calculates the average
            counter++;
            System.out.println("------------------------------");
            System.out.println("A: " + letters[0]);
            System.out.println("B: " + letters[1]);
            System.out.println("C: " + letters[2]);
            System.out.println("D: " + letters[3]);
            System.out.println("F: " + letters[4]);
            System.out.println("Average: " + average);
            System.out.println("------------------------------");
        }
    } while (grade != -1);
    }
    private static void reciprocal() {
        int num;
        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(2);
        primes.add(3); //Starting prime numbers
        ArrayList<Integer> numpfactors = new ArrayList<>(); //Variables store the prime factors
        ArrayList<Integer> sumtoppfactors = new ArrayList<>();
        ArrayList<Integer> sumbottomfactors = new ArrayList<>(); //Top and bottom of the fraction
        ArrayList<Integer> commonfactors = new ArrayList<>();
        ArrayList<Integer> additionfactors = new ArrayList<>();
        int sumbottom = 0;
        int sumtop = 0;
        int lowest = 0;
        int addition = 0;
        int suminaddition = 0;
        int numinaddition = 0;
        int times = 0;
        do {
            times++;
            System.out.println("Enter a number (0 to stop)");
            num = input.nextInt();
            if (num != 0) {
                while (num*sumbottom > primes.get(primes.size()-1) || num*sumtop > primes.get(primes.size()-1)) {
                    primes = primefinder(primes);
                }
                if (sumbottom != 0) {
                    sumtop = sumtop * num + sumbottom; //Adds the two fractions together
                    sumbottom = num*sumbottom;
                    sumbottomfactors = getprimefactors(primes, sumbottom);
                    sumtoppfactors = getprimefactors(primes, sumtop); //Creates prime factors for use in calculation
                    for (int i = 0; i < primes.size(); i++) {
                        lowest = Math.min(sumbottomfactors.get(i), sumtoppfactors.get(i)); //Removes the GCD from both numbers because I'm too dumb to remember that their's a build in method for GCD
                        sumbottomfactors.set(i, sumbottomfactors.get(i) - lowest);
                        sumtoppfactors.set(i, sumtoppfactors.get(i) - lowest);
                        sumbottomfactors.set(i,(int)Math.pow(primes.get(i),sumbottomfactors.get(i))); //Step 1 of the long path from an ArrayList of prime factors to an integer
                        sumtoppfactors.set(i,(int)Math.pow(primes.get(i),sumtoppfactors.get(i)));
                    }
                    sumbottom = 1;
                    sumtop = 1;
                    while (sumbottomfactors.contains(0)) {
                        sumbottomfactors.remove((Integer) 0); //Removes the 0s from the list so that they don't mess up the calculation
                    }
                    while (sumtoppfactors.contains(0)) {
                        sumtoppfactors.remove((Integer) 0);
                    }
                    for (int bottom : sumbottomfactors) {
                        sumbottom *= bottom; //Reconstructs the prime factors so that they are integers
                    }
                    for (int top : sumtoppfactors) {
                        sumtop *= top;
                    }

                } else {
                    sumbottom = num;
                    sumtop++;
                }

                System.out.println("Your sum is: " + sumtop + " / " + sumbottom);
            }
            if (num == 0) {
                System.out.println("Do you want to quit? 0 to quit");
                num = input.nextInt();
            }
        } while (num != 0 && times != 10); //The limit of 10 is 100% artificial; this program can do it until the integers overflow
    }

    private static void  LCMGCD() {
        System.out.println("Enter your first number: ");
        int firstnum = input.nextInt();
        System.out.println("Enter your second number: ");
        int secondnum = input.nextInt();
        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(2);
        primes.add(3); //Starting prime numbers
        int highest;
        int lowest;
        while (firstnum > primes.get(primes.size()-1) || secondnum > primes.get(primes.size()-1)) {
            primes = primefinder(primes);
        }
        ArrayList<Integer> firstnumfac = getprimefactors(primes,firstnum);
        ArrayList<Integer> secondnumfac = getprimefactors(primes,secondnum);
        ArrayList<Integer> lcmnum = new ArrayList<>();
        ArrayList<Integer> gcdnum = new ArrayList<>();
        int lcm = 1;
        int gcd = 1;
        for (int i = 0; i < primes.size(); i++) {
            highest = Math.max(firstnumfac.get(i), secondnumfac.get(i)); //Makes sure that this collects the minimum amount of prime factors required for a gcm
            lcmnum.add(highest);
            lcmnum.set(i,(int)Math.pow(primes.get(i),lcmnum.get(i))); //Step 1 of the long path from an ArrayList of prime factors to an integer
        }
        while (lcmnum.contains(0)) {
            lcmnum.remove((Integer) 0);
        }
        for (int bottom : lcmnum) {
            lcm *= bottom; //Reconstructs the prime factors so that they are integers
        }
        for (int i = 0; i < primes.size(); i++) {
            lowest = Math.min(firstnumfac.get(i), secondnumfac.get(i)); //Makes sure that this collects the minimum amount of prime factors required for a gcm
            gcdnum.add(lowest);
            gcdnum.set(i,(int)Math.pow(primes.get(i),gcdnum.get(i))); //Step 1 of the long path from an ArrayList of prime factors to an integer
        }
        while (lcmnum.contains(0)) {
            gcdnum.remove((Integer) 0);
        }
        for (int bottom : gcdnum) {
            gcd *= bottom; //Reconstructs the prime factors so that they are integers
        }
        System.out.println("The GCD of the two numbers is: " + gcd);
        System.out.println("The LCM of the two numbers is: " + lcm);

    }

    private static ArrayList<Integer> primefinder(ArrayList<Integer> primes) { //This function is needed so we can correctly calculate the reciprocals
        int found = 0;
        int start = (int) primes.get(primes.size() - 1);
        while (found == 0) {
            start += 2;
            System.out.println(primes);
            for (int prime : primes) {
                if (start % prime != 0) { //Tests for primality
                    found = start;
                } else {
                    found = 0;
                    break;
                }
            }
            if (found != 0) {
                primes.add(found);
            }
        }
        return primes;
    }

    private static ArrayList<Integer> getprimefactors(ArrayList<Integer> primes,int tested) {
        ArrayList<Integer> result = new ArrayList<>(primes);
        for (int i = 0; i < result.size(); i++)  {
            result.set(i,0);
        }
        for(int i = 0; i < primes.size(); i++) {
            if (tested % primes.get(i) == 0) {
                result.set(i,result.get(i)+1);
                tested /= primes.get(i);
                i--;
            }
        }
        return result;
    }


}
