package com.example.gist;

public class Pojo {

    public Pojo(){

    }


    public String firstname,lastname,roll,phone,email;
    public Pojo(String firstname, String lastname, String roll, String phone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.roll = roll;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstname() {


        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
