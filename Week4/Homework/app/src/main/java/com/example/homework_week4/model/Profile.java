package com.example.homework_week4.model;

public class Profile {
    private String Name;
    private String Email;
    private String Phone;
    private int Gender;

    public Profile (String Name, String Email, String Phone, int Gender) {
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getGender() {
        return Gender;
    }
}
