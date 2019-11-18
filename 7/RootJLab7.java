//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

import java.util.Scanner;

public class RootJLab7 {
   static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

    public static void main(String[] args) {
        System.out.print("How many primes do you want to find:   ");
        int num_to_find = ensureint();
        int index1;
        int index2;
        
        long[] primearray = new long[num_to_find];
        int response;
        

        primearray = fillprimearray(primearray,num_to_find);

        if (yesno("Do you want to display the primes as a table")) {
            displaytable(primearray);
        }
        String[] items = { "End Program", "Get Prime", "Display Range", "Factors", "Display Table",
                "Rebuild Prime Array" }; // Menu code
        String menu = makemenu(items);

        do {
            System.out.println(menu);
            response = ensureint();
            switch (response) {
            case 1:
                horizontaltabs();
                break;
            case 2:
                numbergame();
                break;
            case 3:
                rectangle();
                break;
            case 4:
                factorials();
                break;
            case 5:
                thousandprime();
                break;
            default:
                break;
            }

        } while (response != 0);

        System.out.println("Bye!");
    
    }
    
    private static String makemenu(String[] items) { // Function generates a menu string so that I don't have to type it
                                                     // out.
        String result = "";
        int length = items.length; // *very* slight optimization

        for (int i = 0; i < length; i++) { // Iterates through the array items and generates a menu entry for each item
                                           // on items
            result += i + ":  " + items[i] + "\n";
        }

        return result;
    }
        do {
            System.out.print("\n Enter the prime number you would like to find (-1 to quit):   ");
            try {
                response = input.nextLine();
                index1 = Integer.parseInt(response.split(" ")[0]);
                if (response.contains(" ")) {
                    index2 = Integer.parseInt(response.split(" ")[1]);
                    displayprimes(primearray, index1, index2);
                }
                else  if (index1 != -1) {
                    displayprime(primearray, index1);
                }
            }
            catch (NumberFormatException e) {
                index1 = 0;
                System.out.print("You need to enter valid integers");
            }
        } while (index1 != -1);
    }

    private static void displayprime(long[] array, int prime) {
        System.out.print("\nFound prime number:  " + array[prime-1]);
    }

    private static void displayprimes(long[] array, int prime1, int prime2) {
        int placehold = prime1;
        prime1 = Math.min(prime2,prime1);
        prime2 = Math.max(prime2,placehold);


        for (int i = 0; i < array.length; i++) {
            if (array[i] <= prime2) { //Split up to slightly increase performance
                if (array[i] >= prime1) {
                    System.out.print("\nFound prime number:  " + array[i]);
                }
            }
        }
    }

    private static int ensureint() {
        boolean isint = false;
        int result = 0;

        while (!isint) {
            if (input.hasNextInt()) {
                result = input.nextInt();
                input.nextLine(); //Consume \n
                isint = true;
            }
            else {
                input.nextLine();
                System.out.print("Please enter an int\n");
            }
        }

        return result;
    }

    private static boolean yesno(String query) {        
        query += "? (y/n)";
        
        do {
            System.out.println(query);

            if (input.nextLine() == "y") {
                return true;
            }
            else if (input.nextLine() == "n") {
                return false;
            }
            else {
                System.out.println("Please put y or n");
            }
        } while (true);
    }

    private static void displaytable(long[] array) {

    }

    private static long[] fillprimearray(long[] primearray) {// Put here to fufill lab requirements
        return fillprimearray(primearray, 1000); 
    }

    private static long[] fillprimearray(long[] primearray, int num_to_find) {
        primearray = sieve(num_to_find, num_to_find); // Max value 2147483645
        return primearray;
    }
    


    private static long[] sieve(int numofprimes,int framesize) {
        long offset = +2;
        int numfound = 0;
        boolean primefound = false;
        long[] results = new long[numofprimes];
        long firstvalue;
        boolean[] primes = new boolean[framesize];
        long prime;

        do {
            for (int i = 0; i < framesize; i++) {
                primes[i] = true;
            }
            
            for (int index = 0; index < numfound; index++) {
                prime = results[index];
                firstvalue = (offset / prime) * prime - offset;
                if (firstvalue < 0L) {
                    firstvalue += prime;
                }
                firstvalue += offset;

                for (long i = firstvalue; i < framesize + offset; i += prime) {
                        primes[(int) (i-offset)] = false;
                }

                
            }

            for (int index = 0; index < framesize; index++) {
                if (primes[index] == true) {
                    prime = index + offset;

                    for (long i = prime; i < framesize + offset; i += prime) {
                        primes[(int) (i-offset)] = false;
                    }
                    
                    results[numfound] = (long) prime;
                    numfound++;
                    if (numfound == numofprimes) {
                        primefound = true;
                        break;

                    }
                }
            }


            offset += framesize;

            
        } while (!primefound);

        return results;
    }
}