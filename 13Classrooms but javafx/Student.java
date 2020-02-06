class Student {
    private int studentnum;
    private int grade;
    private String lastname;
    private String firstname;
    private String gender;
    private String[] data;
    private boolean generic;

    public Student(String[] data) {
        this.studentnum = Integer.parseInt(data[0]);
        this.grade = Integer.parseInt(data[1]);
        this.lastname = data[2];
        this.firstname = data[3];
        this.gender = data[4];
        this.data = data;
        this.generic = false;
    }

    public Student() {
        this(new String[] {"111111","11","Smith","John","Male"});
        this.generic = true;
    }

    public Student copy() {
        return new Student(this.data);
    }

    public int getID() {
        return this.studentnum;
    }

    public int getGrade() {
        return this.grade;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getGender() {
        return this.gender;
    }

    public String[] getData() {
        return this.data;
    }

    public boolean isGeneric() {
        return this.generic;
    }
}
    