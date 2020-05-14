import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.ObjectInputStream;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

class Classroom {
    //Using list properties because implements ObservableList
    private SimpleListProperty<Student> students;
    private String sourcefilename;
    private int filelength;

    //Getter, didn't see a point in making setter or property getter
    public ObservableList<Student> getStudents() {
        return this.students;
    }

    public Student search(String name) {
        // Searches first by last name, than first name, than returns the generic if both fail
        try {
            return search(2, name);
        } catch (NameNotFoundException e0) {
            try {
                return search(3, name);
            } catch (NameNotFoundException e1) {
                return new Student();
            }
        }
    }

    //Searches by ID
    public Student search(int id) {
        try {
            return search(0, Integer.toString(id));
        } catch (NameNotFoundException e) {
            return new Student();
        }
    }

    //This function does the actual searching, just iterates through and checks if the field matches
    private Student search(int field, String data) throws NameNotFoundException{
        Student result = null;
        
        for (int i = 0; i < this.filelength; i++) {
            this.students.get(i).updateData();
            if (this.students.get(i).getData()[field].equals(data)) {
                result = this.students.get(i);
            }
        }

        if (result == null) {
            throw new NameNotFoundException();
        }

        return result;
    }

    //Deletes selected - selected should only be changed by GUI checkboxes (RootJLab13)
    public void deleteSelected() {
        this.students.removeIf(x -> x.getSelected());
        this.filelength = this.students.size();
        save();
    }

    //Deletes a student (should find the student using search)
    public void delete(Student student) {
        this.students.remove(student);
        this.filelength--;
        save();
    }

    //Adds a student from a string - this is mainly a utility method so that I don't need to catch Exceptions (thrown in student)
    //Exceptions are there as a way of error checking on the file inputs
    public void add(String[] student) {
        try {
            add(new Student(student));
            this.filelength++;
        }
        catch (Exception e) {

        }
    }

    public void add(Student student) {
        this.students.add(student);
        save();
    }

    public void sortbyid() {
        StudentIDComparator comparator = new StudentIDComparator();

        sort(comparator);
    }

    public void sortbyname() {
        int maxim = 0;

        for (int i = 0; i < this.filelength; i++) {
            //Need to find largest name, so that you know how much to pad other names 
            maxim = Math.max(this.students.get(i).getLastName().length(), maxim);
        }

        LastNameComparator comparator = new LastNameComparator(maxim);

        sort(comparator);
    }

    //Function that implements the sort - mainly a legacy from when bubblesort was a requirement in the lab, I can use List.sort but won't for reasons
    private void sort(Comparator<Student> comparator) {
        Student swap;
        boolean sorted;

        do {
            sorted = true;
            for (int i = 0; i < this.filelength - 1; i++) {

                if (inttobool(comparator.compare(this.students.get(i), this.students.get(i + 1)))) {
                    swap = this.students.get(i);
                    this.students.set(i, this.students.get(i + 1));
                    this.students.set(i + 1, swap);
                    sorted = false;
                }

            }
        } while (!sorted);
        save();
    }

    //Converts results of Comparator.compare to the o1 > o2
    private boolean inttobool(int result) {
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int[] gradestatistics() {
        String[] lineparts;
        int[] grades = new int[4];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students.get(i).getData();
            grades[Integer.parseInt(lineparts[1]) - 9]++; // Adds one to the appropriate array

        }

        return grades;
    }

    //Uses some really funky math to assign 'M' to 0 and 'F' to 1
    public int[] genderstatistics() {
        String[] lineparts;
        int[] genders = new int[2];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students.get(i).getData();
            genders[((int) lineparts[4].toCharArray()[0] - (int) 'F') / ((int) 'M' - (int) 'F')]++;
        }

        return genders;
    }

    public int[] namestatistics() {
        String[] lineparts;
        int[] lastnames = new int[26];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students.get(i).getData();
            lastnames[(int) lineparts[2].toCharArray()[0] - (int) 'A']++;
        }

        return lastnames;

    }

    //Constructor for custom filename (not in use for Lab 13 but might find in somewhere else)
    Classroom(String filename) throws FileNotFoundException{
        this.students = new SimpleListProperty<Student>(javafx.collections.FXCollections.observableList(new ArrayList<Student>())); 
        load(filename);
    }

    //Default, assumes existence of classlist.txt but will generate empty thing if it either a) doesn't exist or b) is corrupted/incompatible
    Classroom() {
        try {
            load("classlist.txt");
        }
        catch (FileNotFoundException e) {
            this.students = new SimpleListProperty<Student>(javafx.collections.FXCollections.observableList(new ArrayList<Student>())); 
            this.filelength = 0;
            this.sourcefilename = "classlist.txt";
        }
    }

    //Load routine - tries Java first, and if that failes goes to text, and if that fails, errors
    public void load(String filename) throws FileNotFoundException {
        try {
            loadJava(filename);
        }
        catch (Exception e) {
            try {
                loadText(filename);              
            }
            catch (Exception e1) {
                throw new FileNotFoundException();
            }
        }
        save();
    }

    //Legacy loading because can't assume existence of proper file
    private void loadText(String filename) throws Exception{
        this.sourcefilename = filename;
        int filelength = getfilelength();

        if (filelength == 0) {
            throw new FileNotFoundException();
        }

        this.students.clear();
        Scanner filescanner = generatescanner();

        for (int i = 0; i < filelength; i++) {
            this.students.add(new Student(filescanner.nextLine().split(" ")));
        }

        this.filelength = filelength;
    
    }

    //Dep of above function
    private int getfilelength() throws FileNotFoundException{
        Scanner scanner = generatescanner();
        int result = 0;

        while (scanner.hasNextLine()) {
            result++;
            scanner.nextLine();
        }

        return result;
    }

    //Dep of two above functions
    private Scanner generatescanner() throws FileNotFoundException{
        File file = new File(this.sourcefilename);

        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("File not found\n");
            this.sourcefilename = "classlist.txt";
            return new Scanner(new File("classlist.txt"));
        }
    }

    //Loads from a java object file
    private void loadJava(String filename) throws FileNotFoundException {
        try {
            this.sourcefilename = filename;
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(filename));
            this.students.clear();
            this.students.addAll((ArrayList<Student>) oin.readObject());
            this.filelength = this.students.size();
        }
        catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    //Saves to default file
    public void save() {
        save(this.sourcefilename, false);
    }

    //Saves to specific file, and need to specify if saving as java or text
    public void save(String filename, boolean text) {
        if (text) {
            saveText(filename);
        }
        else {
            saveJava(filename);
        }
    }

    private void saveText(String filename) {
        String str = generatestring();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            System.out.print("Caught an error\n");
        }
    }

    public String generatestring() {
        String str = "";
        for (int i = 0; i < this.students.getSize(); i++) {
            str += Integer.toString(this.students.get(i).getID()) + " ";
            str += Integer.toString(this.students.get(i).getGrade()) + " ";
            str += this.students.get(i).getLastName() + " ";
            str += this.students.get(i).getFirstName() + " ";
            str += this.students.get(i).getGender();

            str += "\n";
        }
        return str;
    }

    private void saveJava(String filename) {
        try {
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filename));
            oout.writeObject(new ArrayList<Student>(this.students));
            oout.close();
        }
        catch (Exception e) {
            System.out.println("Caught an error! " + e.toString());
        }
    }

}
