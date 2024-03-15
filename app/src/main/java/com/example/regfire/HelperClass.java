package com.example.regfire;

public class HelperClass {

    String name, email, username, password, dob; // Added dob field

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Added getter and setter for dob
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public HelperClass(String name, String email, String dob, String username, String password) { // Updated constructor
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }
}
