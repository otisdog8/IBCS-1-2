import java.io.Serializable;

class Student implements Serializable{
    int studentnumber;
    int grade;
    String lastname;
    String firstname;
    String gender;
    String[] data;
    boolean generic;

    public Student(String[] data) {
        this.studentnumber = Integer.parseInt(data[0]);
        this.grade = Integer.parseInt(data[1]);
        this.lastname = data[2];
        this.firstname = data[3];
        this.gender = data[4];
        this.data = data;
    }

    public Student() {
        this(new String[] {"111111","11","Smith","John","Male"});
        this.generic = true;
    }

    public Student(int studentnumber, int grade, String lastname, String firstname, String gender) {
        //Method is depricated and should not be used in actual programs
        this.studentnumber = studentnumber;
		this.grade         = grade;
		this.lastname      = lastname;
		this.firstname     = firstname;
        this.gender        = gender;	
        this.data = new String[]{Integer.toString(studentnumber), Integer.toString(grade), lastname, firstname, gender};
    }

    public Student copy() {
        return new Student(this.data);
    }
}
    