//package com.hospital.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "patients")
//public class Patient {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private int age;
//    private String gender;
//    private String disease;
//    private String contact;
//
//    // Getters and Setters
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//    
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//    
//    public int getAge() { return age; }
//    public void setAge(int age) { this.age = age; }
//    
//    public String getGender() { return gender; }
//    public void setGender(String gender) { this.gender = gender; }
//    
//    public String getDisease() { return disease; }
//    public void setDisease(String disease) { this.disease = disease; }
//    
//    public String getContact() { return contact; }
//    public void setContact(String contact) { this.contact = contact; }
//}



package com.hospital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private String gender;
    private String disease;
    private String contact;

    // Constructors
    public Patient() {}

    public Patient(String name, int age, String gender, String disease, String contact) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.contact = contact;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
