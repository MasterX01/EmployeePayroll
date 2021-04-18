package com.bridgelabz.employeepayroll;

import java.time.LocalDate;

public class EmployeeDBData {
    public int empID;
    public String name;
    public double salary;
    public String gender;
    public LocalDate start;
    public String dept;

    public EmployeeDBData(int empID, String name, double salary, String gender, LocalDate start){
        this.empID = empID;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.start = start;
    }

    public EmployeeDBData(String name, double salary, String gender, LocalDate start, String dept){
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.start = start;
        this.dept = dept;
    }

    public void setEmpID(int empID) { this.empID = empID; }

    public void setName(String name) { this.name = name; }

    public void setSalary(double salary) { this.salary = salary; }

    public void setGender(String gender) { this.gender = gender; }

    public void setStart(LocalDate start) { this.start = start; }

    public int getEmpID() { return empID; }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public String getGender() { return gender; }

    public LocalDate getStart() { return start; }
}
