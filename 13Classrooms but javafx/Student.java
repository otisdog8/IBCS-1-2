class Student {
    int studentnum;
    int grade;
    String lastname;
    String firstname;
    String gender;
    String[] data;

    public Student(String[] data) {
        this.studentnum = Integer.parseInt(data[0]);
        this.grade = Integer.parseInt(data[1]);
        this.lastname = data[2];
        this.firstname = data[3];
        this.gender = data[4];
        this.data = data;
    }

    public Student copy() {
        return new Student(this.data);
    }
}
    