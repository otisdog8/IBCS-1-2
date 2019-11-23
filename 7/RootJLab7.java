//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class RootJLab7 {
    Scanner input; // Makes it so that everyone can use this Scanner
    long[] primearray;
    // Table Configuration Values
    int linelength = 19;
    int columnlength = 10;

    public static void main(String[] args) {
        RootJLab7 lab = new RootJLab7();
        lab.run();
    }

    RootJLab7() {
        this.input = new Scanner(System.in);
        this.primearray = buildarray();
    }

    public void run() {
        int response;
        String[] items = { "End Program", "Get Prime", "Display Range", "Factors", "Display Table",
                "Save Primes To File", "Load primes From File", "Rebuild Prime Array", "Modify Table Settings" }; // Menu
                                                                                                                  // code
        String menu = makemenu(items);

        do {
            System.out.println(menu);
            response = ensureint();
            switch (response) {
            case 1:
                getprime(); // primearray kept here for efficency (not constantly regenerating)
                break;
            case 2:
                displayprimerange();
                break;
            case 3:
                factors();
                break;
            case 4:
                displaytable(primearray);
                break;
            case 5:
                savefile();
                break;
            case 6:
                loadfile();
                break;
            case 7:
                rebuildarray();
                break;
            case 8:
                tablemod();
                break;
            default:
                break;
            }

        } while (response != 0);

        System.out.println("Bye!");
    }

    private String makemenu(String[] items) { // Function generates a menu string so that I don't have to type it
                                              // out.
        String result = "";
        int length = items.length; // *very* slight optimization

        for (int i = 0; i < length; i++) { // Iterates through the array items and generates a menu entry for each item
                                           // on items
            result += i + ":  " + items[i] + "\n";
        }

        return result;
    }

    private void rebuildarray() {
        primearray = buildarray();
    }

    private long[] buildarray() {
        System.out.print("How many primes do you want to find:   ");
        int num_to_find = ensureint();
        return fillprimearray(primearray, num_to_find);
    }

    private static long[] fillprimearray(long[] primearray) {// Put here to fufill lab requirements
        return fillprimearray(primearray, 1000);
    }

    private static long[] fillprimearray(long[] primearray, int num_to_find) {
        return sieve(num_to_find, num_to_find); // Max value 2147483645
    }

    private void getprime() {
        // Continuously gets primes
        System.out.print("Enter the nth prime you want to find (0 to quit):   ");
        int prime = ensureint();
        do {
            System.out.println("Your prime is:  " + primearray[prime - 1]);
            System.out.print("Enter the nth prime you want to find (0 to quit):   ");
            prime = ensureint();

        } while (prime != 0);
    }

    private void displayprimes(int prime1, int prime2) {
        // Displays the primes between the two integers
        LongArrayPlusPlus displayvals = new LongArrayPlusPlus();
        int indexoffset = 0;

        int placehold = prime1;

        prime1 = Math.min(prime2, prime1);
        prime2 = Math.max(prime2, placehold);
        // Swaps the two if they are out of order

        for (int i = 0; i < primearray.length; i++) {
            if (primearray[i] <= prime2) { // Split up to slightly increase performance
                if (primearray[i] >= prime1) {
                    displayvals.add(primearray[i]);
                } else {
                    indexoffset++;
                    // Makes the index offset when a table is generated for the results of this
                    // query
                }
            }
        }
        displaytable(displayvals.getinternalarray(), indexoffset);
        System.out.println();
    }

    private void displayprimerange() {
        System.out.print("Enter your first number:   ");
        int num1 = ensureint();
        System.out.print("Enter your second number:   ");
        int num2 = ensureint();
        displayprimes(num1, num2);
    }

    private void displaytable(long[] array) {
        displaytable(array, 0);
    }

    private void displaytable(long[] array, int startindex) {
        int numlines = (int) Math.ceil(array.length / (double) linelength);
        int numcolumns;
        int numlength;
        int index;
        String indexstring;

        System.out.println();
        for (int i = 0; i < numlines; i++) {
            System.out.println();
            // Print indexes
            for (int j = 0; j < linelength; j++) {
                index = j + 1 + i * linelength + startindex;
                indexstring = Integer.toString(index);
                // But not 11, 12, or 13 because they are SPECIAL
                if (index % 100 - index % 10 == 10) {
                    // If 10s place is 1
                    // 11th, 12th, 13th
                    indexstring += "th";
                } else {
                    switch ((index) % 10) {
                    // Gets ones place
                    case 1:
                        indexstring += "st";
                        break;
                    case 2:
                        indexstring += "nd";
                        break;
                    case 3:
                        indexstring += "rd";
                        break;
                    default:
                        indexstring += "th";
                        break;
                    }
                }
                numlength = indexstring.length();
                System.out.print(indexstring);
                for (int k = 0; k < columnlength - numlength; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            // Print primes
            if (i == numlines - 1) {
                // Limited number of table displays
                numcolumns = array.length - i * linelength;
            } else {
                numcolumns = linelength;
            }
            for (int j = 0; j < numcolumns; j++) {
                numlength = (int) Math.floor(Math.log10(array[i * linelength + j])) + 1; // Gets the length in
                                                                                         // characters of the number
                System.out.print(array[i * linelength + j]);
                for (int k = 0; k < columnlength - numlength; k++) {
                    System.out.print(" "); // Printes a needed number of spaces
                }
            }
            System.out.println();
        }

    }

    private void tablemod() {
        System.out.print("Enter the number of columns per line (default 19):     ");
        linelength = ensureint();
        System.out.print("Enter the size of each column (default 10):     ");
        columnlength = ensureint();
    }

    private void factors() {
        System.out.print("Enter the number you want to factor (0 to quit):   ");
        long factornum = ensurelong();
        LongArrayPlusPlus factors;
        do {
            factors = factor(factornum);
            System.out.print("Your factors are:  ");
            for (int i = 0; i < factors.length; i++) {
                System.out.print(factors.get(i) + " ");
            }
            System.out.println();
            System.out.print("Enter the number you want to factor (0 to quit):   ");
            factornum = ensurelong();

        } while (factornum != 0);
    }

    private LongArrayPlusPlus factor(long factornum) {
        LongArrayPlusPlus factors = new LongArrayPlusPlus();
        // No ArrayLists were used in the creation of this program
        int checkindex = 0;
        int primecheckindex = 0;
        boolean prime = false;
        boolean exit = false;

        if (factornum == 1) {
            prime = true;
            factors.add(1L);
            // Make it have at least *one* factor
        }

        do {
            // Checks if prime
            primecheckindex = checkindex;

            if (checkindex > primearray.length - 1) {
                primearray = sieve(primearray.length * 2, primearray.length * 2); // Dynamically expand the prime array
                                                                                  // we're using if we are about to
                                                                                  // exceed it (without finding results)
            }
            while (!prime && !exit) {
                if (primecheckindex > primearray.length - 1) {
                    // It is now unreasonable to check for exact primality because we exceed the
                    // bounds of the array
                    primecheckindex = primearray.length - 1;
                    exit = true;

                }
                if (Math.pow(primearray[primecheckindex], 2) > factornum) {
                    exit = true;
                }
                if (primearray[primecheckindex] == factornum) {
                    factors.add(primearray[primecheckindex]);
                    factornum = 1; // Prevents the last factor from being added twice
                    prime = true;
                    exit = true;
                }
                primecheckindex++;
            }

            // Trial division (with primes only)

            if (factornum % primearray[checkindex] == 0) {
                factornum /= primearray[checkindex];
                factors.add(primearray[checkindex]);
                if (factornum == 1) {
                    // Otherwise it'll hang on one
                    prime = true;
                }
            } else {
                checkindex++;
                exit = false;
            }

        } while (!prime);

        return factors;
    }

    private static long[] sieve(int numofprimes, int framesize) {
        long offset = +2;
        // There was a better way to code this algorithm that I only discovered today
        // (11/22)
        // Use an array of booleans and mark off normally. Use the array of booleans to
        // find all the primes
        // It might be superior to my previous method
        int numfound = 0;
        boolean primefound = false;
        long[] results = new long[numofprimes];
        // Array to store all primes found in (can get memory intensive with large size)
        long firstvalue;
        boolean[] primes = new boolean[framesize];
        long prime;

        do {
            for (int i = 0; i < framesize; i++) {
                primes[i] = true;
            } // Sets up the sieve array for marking

            for (int index = 0; index < numfound; index++) {
                prime = results[index];
                firstvalue = (offset / prime) * prime - offset;
                if (firstvalue < 0L) {
                    firstvalue += prime;
                }
                firstvalue += offset;

                for (long i = firstvalue; i < framesize + offset; i += prime) {
                    primes[(int) (i - offset)] = false;
                }

            } // Marks off primes that have already been discovered on an index change

            for (int index = 0; index < framesize; index++) {
                if (primes[index] == true && numfound != numofprimes) {
                    // Loops through the array finding primes that the algorithm already calculated
                    prime = index + offset;

                    for (long i = prime; i < framesize + offset; i += prime) {
                        primes[(int) (i - offset)] = false; // Actually marks numbers off in the alglrithm
                    }

                    results[numfound] = (long) prime; // Adds the found prime to the results
                    numfound++;
                    if (numfound == numofprimes) {
                        primefound = true;
                    }
                }
            }

            offset += framesize;
            // Modifies the offset for another iteration

        } while (!primefound);

        return results;
    }

    private void savefile() {
        try {
            ObjectOutputStream filewriter = new ObjectOutputStream(new FileOutputStream("primes.jobj"));
            filewriter.writeObject(primearray);
            // Writes the array of primes to a file
            filewriter.close();
        } catch (IOException e) {

        } finally {
            // Make sure to catch these and do nothing
        }
    }

    private void loadfile() {
        try {
            ObjectInputStream filereader = new ObjectInputStream(new FileInputStream("primes.jobj"));

            primearray = (long[]) filereader.readObject();
            // Reads the array of primes from a file

            filereader.close();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            // Make sure to catch these and do nothing
        }
    }

    private int ensureint() {
        boolean isint = false;
        int result = 0;

        while (!isint) {
            if (input.hasNextInt()) {
                result = input.nextInt();
                // input.nextLine(); // Consume \n
                isint = true;
            } else {
                input.nextLine();
                System.out.print("Please enter an int\n");
            }
        }

        return result;
    }

    private long ensurelong() {
        boolean isint = false;
        long result = 0;

        while (!isint) {
            if (input.hasNextLong()) {
                result = input.nextLong();
                // input.nextLine(); // Consume \n
                isint = true;
            } else {
                input.nextLine();
                System.out.print("Please enter an int\n");
            }
        }

        return result;
    }
}

class LongArrayPlusPlus {
    // Not using generic typing because Objects use ~2.5x more memory than longs
    long[] internalarray;
    int length;

    LongArrayPlusPlus(long[] source) {
        this.internalarray = source;
        this.length = source.length;
    }

    LongArrayPlusPlus() {
        this.internalarray = new long[0];
        this.length = 0;
    }

    public void add(long addnum) {
        long[] newarray = new long[internalarray.length + 1];

        for (int i = 0; i < internalarray.length; i++) {
            newarray[i] = internalarray[i];
        }

        newarray[internalarray.length] = addnum;
        this.internalarray = newarray;
        this.length++;
    }

    public void delete(int index) {
        long[] newarray = new long[internalarray.length - 1];
        int offset = 0;

        for (int i = 0; i < internalarray.length; i++) {
            if (i == index) {
                offset++;
            } else {
                newarray[i - offset] = internalarray[i];
            }
        }

        this.internalarray = newarray;
        this.length--;
    }

    public long pop(int index) {
        // Allows for people to know what they're deleting as they delete it
        long[] newarray = new long[internalarray.length - 1];
        int offset = 0;
        long retval = internalarray[index];

        for (int i = 0; i < internalarray.length; i++) {
            if (i == index) {
                offset++;
            } else {
                newarray[i - offset] = internalarray[i];
            }
        }

        this.internalarray = newarray;
        this.length--;

        return retval;
    }

    public long get(int index) {
        return internalarray[index];
    }

    public long[] getinternalarray() {
        return internalarray;
    }

}