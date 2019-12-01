package com.example.swe_classroom_problem__solution;

public class User {
   private String Name;
   private String Email;
   private String phoneNo;

    public User(String name, String email, String phoneNo) {
        Name = name;
        Email = email;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
