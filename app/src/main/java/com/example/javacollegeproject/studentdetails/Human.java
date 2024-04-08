package com.example.javacollegeproject.studentdetails;

 class Human {

    private String name;
    private String gender;

     public Human(String name, String gender) {
         this.name = name;
         this.gender = gender;
     }

     public Human(){
         this.gender="undefine";
         this.name="undefine";
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getGender() {
         return gender;
     }

     public void setGender(String gender) {
         this.gender = gender;
     }
 }
