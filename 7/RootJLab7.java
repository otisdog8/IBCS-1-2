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
    static Scanner input = new Scanner(System.in); // Makes it so that everyone can use this Scanner

    public static void main(String[] args) {
        long[] primearray;
        int response;

        primearray = rebuildarray();

        String[] items = { "End Program", "Get Prime", "Display Range", "Factors", "Display Table",
                "Save Primes To File", "Load primes From File", "Rebuild Prime Array" }; // Menu code
        String menu = makemenu(items);

        do {
            System.out.println(menu);
            response = ensureint();
            switch (response) {
            case 1:
                getprime(primearray); // primearray kept here for efficency (not constantly regenerating)
                break;
            case 2:
                displayprimerange(primearray);
                break;
            case 3:
                factors(primearray);
                break;
            case 4:
                displaytable(new LongArrayPlusPlus(primearray));
                break;
            case 5:
                savefile(primearray);
                break;
            case 6:
                primearray = loadfile();
                break;
            case 7:
                primearray = rebuildarray();
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

    private static void getprime(long[] primes) {
        System.out.print("Enter the nth prime you want to find (0 to quit):   ");
        int prime = ensureint();
        do {
            System.out.println("Your prime is:  " + primes[prime - 1]);
            System.out.print("Enter the nth prime you want to find (0 to quit):   ");
            prime = ensureint();

        } while (prime != 0);
    }

    private static void displayprimes(long[] array, int prime1, int prime2) {
        LongArrayPlusPlus displayvals = new LongArrayPlusPlus();

        int placehold = prime1;
        prime1 = Math.min(prime2, prime1);
        prime2 = Math.max(prime2, placehold);

        for (int i = 0; i < array.length; i++) {
            if (array[i] <= prime2) { // Split up to slightly increase performance
                if (array[i] >= prime1) {
                    displayvals.add(array[i]);
                }
            }
        }
        displaytable(displayvals);
        System.out.println();
    }

    private static void displayprimerange(long[] primes) {
        System.out.print("Enter your first number:   ");
        int num1 = ensureint();
        System.out.print("Enter your second number:   ");
        int num2 = ensureint();
        displayprimes(primes, num1, num2);
    }

    private static void factors(long[] primes) {
        System.out.print("Enter the number you want to factor (0 to quit):   ");
        long factornum = ensurelong();
        LongArrayPlusPlus factors;
        do {
            factors = factor(factornum, primes);
            System.out.print("Your factors are:  ");
            for (int i = 0; i < factors.length; i++) {
                System.out.print(factors.get(i) + " ");
            }
            System.out.println();
            System.out.print("Enter the number you want to factor (0 to quit):   ");
            factornum = ensurelong();

        } while (factornum != 0);
    }

    private static void savefile(long[] primes) {
        // String filename = input.nextLine();
        try {
            ObjectOutputStream filewriter = new ObjectOutputStream(new FileOutputStream("primes.jobj"));
            filewriter.writeObject(primes);
            filewriter.close();
        } catch (IOException e) {

        } finally {

        }
    }

    private static long[] loadfile() {
        long[] primearray = new long[0];
        try {
            ObjectInputStream filereader = new ObjectInputStream(new FileInputStream("primes.jobj"));

            primearray = (long[]) filereader.readObject();

            filereader.close();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return primearray;

    }

    private static int ensureint() {
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

    private static long ensurelong() {
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

    private static boolean yesno(String query) {
        query += "? (y/n)";

        do {
            System.out.println(query);

            if (input.nextLine() == "y") {
                return true;
            } else if (input.nextLine() == "n") {
                return false;
            } else {
                System.out.println("Please put y or n");
            }
        } while (true);
    }

    private static void displaytable(LongArrayPlusPlus array) {
        int linelength = 19;
        int columnlength = 10;
        int numlines = (int) Math.ceil(array.length / (double) linelength);
        int numcolumns;
        int numlength;
        String indexstring;

        System.out.println();
        for (int i = 0; i < numlines; i++) {
            System.out.println();
            // Print indexes
            for (int j = 0; j < linelength; j++) {
                indexstring = Integer.toString(j + 1 + i * linelength);
                switch (j + 1 + i * linelength % columnlength) {
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
                numlength = (int) Math.floor(Math.log10(array.get(i * linelength + j))) + 1;
                System.out.print(array.get(i * linelength + j));
                for (int k = 0; k < columnlength - numlength; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    private static long[] rebuildarray() {
        System.out.print("How many primes do you want to find:   ");
        int num_to_find = ensureint();
        long[] primearray = new long[num_to_find];
        primearray = fillprimearray(primearray, num_to_find);
        return primearray;
    }

    private static long[] fillprimearray(long[] primearray) {// Put here to fufill lab requirements
        return fillprimearray(primearray, 1000);
    }

    private static long[] fillprimearray(long[] primearray, int num_to_find) {
        primearray = sieve(num_to_find, num_to_find); // Max value 2147483645
        return primearray;
    }

    private static LongArrayPlusPlus factor(long factornum, long[] primes) {
        LongArrayPlusPlus factors = new LongArrayPlusPlus();
        int checkindex = 0;
        int primecheckindex = 0;
        boolean prime = false;
        boolean exit = false;

        if (factornum == 1) {
            prime = true;
        }

        do {
            // Checks if prime
            primecheckindex = checkindex;

            if (checkindex > primes.length - 1) {
                primes = sieve(primes.length * 2, primes.length * 2);
            }
            while (!prime && !exit) {
                if (primecheckindex > primes.length - 1) {
                    primecheckindex = primes.length - 1;
                    exit = true;
                }
                if (primes[primecheckindex] * primes[primecheckindex] > factornum) {
                    exit = true;
                }
                if (primes[primecheckindex] == factornum) {
                    factors.add(primes[primecheckindex]);
                    factornum = 1;
                    prime = true;
                    exit = true;
                }
                primecheckindex++;
            }

            // Trial division (with primes only)

            if (factornum % primes[checkindex] == 0) {
                factornum /= primes[checkindex];
                factors.add(primes[checkindex]);
                if (factornum == 1) {
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
                    primes[(int) (i - offset)] = false;
                }

            }

            for (int index = 0; index < framesize; index++) {
                if (primes[index] == true) {
                    prime = index + offset;

                    for (long i = prime; i < framesize + offset; i += prime) {
                        primes[(int) (i - offset)] = false;
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

class LongArrayPlusPlus {
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

}