package com.endicott.edu.models;
import com.endicott.edu.simulators.FacultyManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implemented 9-28-17 by Mazlin Higbee
 * mhigb411@mail.endicott.edu
 */
public class FacultyModel implements Serializable {

    private String facultyID; // a unique id for that member of the faculty.
    private String collegeID;
    private String facultyName; //simply the name
    private String title; //EX: Assoicate prof, Dean, VP...
    private String department; //department of the faculty member EX: Math, Computer Science, Biology
    private int salary;   //private int salary = 115000; //yearly salary
    private String officeLocation; //office building and number
    private int happiness;
    private int performance;
    private ArrayList<StudentModel> advisees;
    private Boolean raiseRecentlyGiven;

    public FacultyModel(){}

    public FacultyModel(String facultyName, String title, String department, int salary, String officeLocation, String facultyID) {
        System.out.print("Other constructor hit");
        this.facultyName = facultyName;
        this.title = title;
        this.department = department;
        this.officeLocation = officeLocation;
        this.facultyID = facultyID;
    }

    public FacultyModel(String facultyName, String title, String department, String officeLocation, String collegeID, int salary) {
        this.advisees = new ArrayList<StudentModel>();
        this.facultyName = facultyName;
        this.title = title;
        this.department = department;
        this.officeLocation = officeLocation;
        this.collegeID = collegeID;
        this.salary = salary;
        this.facultyID = FacultyManager.generateFacultyID(this);
        this.performance = 75;
        this.raiseRecentlyGiven = false;
        FacultyManager.computeFacultyHappiness(this, false);
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }
    public String getFacultyID() { return facultyID; }
    public void setFacultyID(String facultyID) { this.facultyID = facultyID;}

    public void setHappiness(int happiness){ this.happiness = happiness; }
    public int getHappiness() { return this.happiness; }

    public void setCollegeID(String collegeID){ this.collegeID = collegeID; }
    public String getCollegeID() { return this.collegeID; }

    public int getPerformance(){ return this.performance; }
    public void setPerformance(int performance) { this.performance = performance; }

    public void addAdvisee(StudentModel student){ advisees.add(student); }
    public ArrayList<StudentModel> getAdvisees() { return this.advisees; }

    public void setRaiseRecentlyGiven(Boolean raiseRecentlyGiven){ this.raiseRecentlyGiven = raiseRecentlyGiven; }
    public Boolean getRaiseRecentlyGiven(){ return this.raiseRecentlyGiven; }
}
