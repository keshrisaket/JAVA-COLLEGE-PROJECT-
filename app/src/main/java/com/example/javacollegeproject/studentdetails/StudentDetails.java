package com.example.javacollegeproject.studentdetails;

public class StudentDetails extends Human {

    private String college;
    private  float roll;
    private  String course ;

    public StudentDetails(String name, String gender, String college, float roll, String course) {
        super(name, gender);
        this.college = college;
        this.roll = roll;
        this.course = course;
    }

  public StudentDetails(){
        super();
      this.college = "Not Admitted";
      this.roll = -1;
      this.course = "Not Admitted";
  }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
