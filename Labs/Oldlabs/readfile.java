import java.io.*;
import java.util.Random;


 class student {
	long studentnumber;
	int grade;
	String lastname;
	String firstname;
	String gender;
	
	
	student(int studentnumber, int grade, String lastname, String firstname, String gender) {
		this.studentnumber = studentnumber;
		this.grade         = grade;
		this.lastname      = lastname;
		this.firstname     = firstname;
		this.gender        = gender;	
	}//constructor for student
	
}//studentclass

public class readfile {
	
//The next function simply prints out the student array that gets filled up in the 
//program below. It is not needed, but I used it to debug.	
public static void printstudentfile(student mystudents[],int numofstudents){
		int i;
		student temp;
		
		for (i=0; i<numofstudents-1; i++) {
			temp = mystudents[i];
			System.out.println(temp.studentnumber + " " + temp.grade + " " + temp.lastname + " " + temp.firstname
			                 + " " + temp.gender);
		}
} //printstudentfile


 public static void main(String args[]) {
   
    int i, success;
	
	Random randnum = new Random();
	
	student mystudent[] = new student[170];
	int numofstudents = 0;
	
   
    //Random randnum = new Random();
	byte inbuf[] = new byte[20500];     //Input buffer for the whole file
	String str = new String();
	
	//Data for one student peeled out one line at a time
	int studentid;
	String studentgender = new String();
	String lastname = new String();
	String firstname =  new String();
	int studentgrade;
	
	
	int indexofspace;
	int tabindex;
    
	
	int closestwhitespace;
	Boolean notfinished = true;

	//The input to this program is a series of lines in a file with each line looking like the one below.
	//The lines are separated by the line feed character '\n'. This loop goes through each line peeling out
	//the student id, gender, lastname, firstname and ignoring everything else. Here is an example of one
	//input line: (With pertinent info changed to protect the innocent)
	//220000	Male	12	12/12/1212	Brim-Edwards, Jackson		9C946F08-5A22-4943-8C52-09E5D54BDFD7		English	Jackson	Brim-Edwards
  
    /*This program then takes the data mentioned above and constructs an object of type student which is added
	  to the mystudent (an array of all my students). Once this is done, I write the array out to a file
	  in character format (in other words, readable) with a newline attached at the end of each line
	*/
	
	
	//All the data is contained in a file called allstudents.TXT. We open a BYTEstream and read it in
	//to a buffer called inbuf. 
	
    try (DataInputStream dataIn = 
             new DataInputStream(new FileInputStream("allstudents.TXT"))) {
				success=dataIn.read(inbuf);
				
                if (success != -1) {
			       for (i=0; i<success; i++)
					str += (char)inbuf[i];
				   System.out.println(str);
				   System.out.println(str.indexOf('\n'));
				}
				
				while ((str != "") & notfinished) {
					indexofspace = str.indexOf('\t');
				
					studentid = Integer.parseInt(str.substring(0,str.indexOf('\t')));
					
					str = str.substring(str.indexOf('\t'),str.length());
				    str = str.trim();
					
					//Get the student gender
					studentgender = str.substring(0,str.indexOf('\t'));
					
					str = str.trim();
					str = str.substring(str.indexOf('\t'),str.length());
					str = str.trim();
					
					//Get the student gradelevel
					studentgrade = Integer.parseInt(str.substring(0,str.indexOf('\t')));
					
					str = str.substring(str.indexOf('\t'),str.length());
					str = str.trim();
					
					//Skip over the birthdate
					str = str.substring(str.indexOf('\t'),str.length());
					str = str.trim();
					
					lastname=str.substring(0,str.indexOf(','));
					System.out.println();
					System.out.println("Student lastname is " + lastname);
					
					str = str.substring(str.indexOf(',') + 1,str.length());
					str = str.trim();
					
					//The next delimeter may be a space or a tab. Find the index of each and use the lower
					//non-zero one. The reason this is all screwy is that some entries have a middle initial
					//and others don't. It is not clear whether there is a space or a tab after the first name
					
					
					indexofspace=str.indexOf(' ');
					tabindex=  str.indexOf('\t');
					
					closestwhitespace = tabindex;
					if (indexofspace != -1)
						if (indexofspace < closestwhitespace) closestwhitespace = indexofspace;	
					
					firstname=str.substring(0,closestwhitespace);
					System.out.println();
					System.out.println("Student firstname is " + firstname);
					
					
					//Skip to the linefeed
					
					notfinished = str.indexOf('\n') > 0;
					str = str.substring(str.indexOf('\n')+1, str.length());
					str.trim();
					
					//Now, construct a new student object with the data and add it to
					//the mystudent array.
					
					mystudent[numofstudents]=new student(studentid, studentgrade, lastname, firstname, studentgender);
			        numofstudents++;
		  }; //while
              
    } catch (IOException exc) {
        System.out.println("Read error.");
        return;
    }

	
	//Now, print out the student array to the screen
	//printstudentfile(mystudent,numofstudents);
	
	//Now, print out the student array to a file called classlist.txt using Character Streams.
	
	try (FileWriter fw  =  
             new FileWriter("classlist.txt")) {
				 int j;
		         student temp;
		         for (i=0; i<numofstudents-1; i++) {
			        temp = mystudent[i];
					j = randnum.nextInt(1000);
					if (j==0) j++;
					fw.write(String.valueOf(temp.studentnumber - j)); //This changes the studentnum so it is unidentifiable
					fw.write(" ");
					fw.write(String.valueOf(temp.grade));
					fw.write(" ");
					fw.write(temp.lastname);
					fw.write(" ");
					fw.write(temp.firstname);
					fw.write(" ");
					fw.write(temp.gender);
					fw.write(" \n");
					
		         }
              
    } catch (IOException exc) {
        System.out.println("Write error.");
        return;
    }

    
	
 }//main 

    

 }//num  

 
   