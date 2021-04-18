package com.bridgelabz.employeepayrolltest;

import com.bridgelabz.employeepayroll.EmployeeDBData;
import com.bridgelabz.employeepayroll.EmployeeDBService;
import com.bridgelabz.employeepayroll.EmployeePayrollService.IOService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EmployeePayrollDBTest {
    @Test
    public void given3EmployeesInDB_whenChecked_ShouldReturnEmployeeCount() throws SQLException, ClassNotFoundException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        List<EmployeeDBData> employeeDBDataList = employeeDBService.retrieveDBData(EmployeeDBService.IO_Service.DB_IO);
        Assertions.assertEquals(3, employeeDBDataList.size());
    }

    @Test
    public void givenDBWith3Employees_whenUpdatingSalary_ShouldMatchWithGivenSalary() throws SQLException, ClassNotFoundException, InterruptedException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        String name = "Teresa";
        double salary = 2500000;
        EmployeeDBData employeeSalary = employeeDBService.updateSalary(name,salary);
        Assertions.assertEquals(salary, employeeSalary.getSalary());
    }

    @Test
    public void givenDateRange_ShouldReturnEmployeesWhoJoinedInDateRange() throws SQLException, ClassNotFoundException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        String startDate = "2018-01-01";
        String endDate = "2020-01-01";
        List<EmployeeDBData> employeeDBDataList = employeeDBService.retrieveDataInDateRange(startDate, endDate);
        Assertions.assertEquals(2, employeeDBDataList.size());
    }

    @Test
    public void givenEmployeeDatabase_ShouldReturnSumOfSalaryForFemales() throws SQLException, ClassNotFoundException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        double femaleSalary = employeeDBService.getTotalSalaryForGender("F");
        Assertions.assertEquals(2500000, femaleSalary);
    }

    @Test
    public void givenEmployeeDatabase_ShouldReturnAvgOfSalaryForFemales() throws SQLException, ClassNotFoundException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        double femaleSalary = employeeDBService.getAvgSalaryForGender("F");
        Assertions.assertEquals(2500000, femaleSalary);
    }

    @Test
    public void givenEmployeeDatabase_ShouldReturnMinSalaryForFemales() throws SQLException, ClassNotFoundException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        double femaleSalary = employeeDBService.getMinSalaryForGender("F");
        Assertions.assertEquals(2500000, femaleSalary);
    }

    @Test
    public void givenEmployeeDatabase_ShouldReturnMaxSalaryForFemales() throws SQLException, ClassNotFoundException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        double femaleSalary = employeeDBService.getMaxSalaryForGender("F");
        Assertions.assertEquals(2500000, femaleSalary);
    }

    @Test
    public void givenEmployeeDatabase_ShouldReturnCountOfFemales() throws SQLException, ClassNotFoundException {
        EmployeeDBService employeeDBService = new EmployeeDBService();
        int femaleSalary = employeeDBService.getCountGender("F");
        Assertions.assertEquals(1, femaleSalary);
    }

    @Test
    public void givenDetails_whenAddedToDatabase_ShouldReflectInList() throws ClassNotFoundException, SQLException{
        EmployeeDBService employeeDBService = new EmployeeDBService();
        String name = "Akash";
        double salary = 3500000;
        String gender = "M";
        String start = "2012-12-12";
        String dept = "DevOps";
        List<EmployeeDBData> employeeList = employeeDBService.addNewEmployee(name, salary, gender, start, dept);
        Assertions.assertEquals(name, employeeList.get(0).name);
    }

    @Test
    public void givenName_ShouldRetrieveDataForActiveEmployees() throws ClassNotFoundException, SQLException{
        EmployeeDBService employeeDBService = new EmployeeDBService();
        String name = "Akash";
        int success = employeeDBService.deleteEmployee(name);
        Assertions.assertEquals(1, success);
    }

    @Test
    public void givenMultipleEmployee_ShouldAddToDBUsingThreads() throws InterruptedException, ClassNotFoundException, SQLException{
        EmployeeDBData[] employeeDBDatas = {
            new EmployeeDBData("Vaishali", 1000000, "F",  Date.valueOf("2017-08-15").toLocalDate(), "HR"),
            new EmployeeDBData("Abhinav", 1000000, "M",  Date.valueOf("2015-08-15").toLocalDate(), "Cleaning"),
            new EmployeeDBData("Ashish", 1000000, "M",  Date.valueOf("2021-08-15").toLocalDate(), "Marketing"),
            new EmployeeDBData("Utkarsh", 1000000, "M",  Date.valueOf("2020-05-15").toLocalDate(), "Security"),
        };
        EmployeeDBService employeeDBService = new EmployeeDBService();
        Instant start = Instant.now();
        employeeDBService.addMultipleEmployees(Arrays.asList(employeeDBDatas));
        Instant end = Instant.now();
        System.out.println("Duration to add employee: " + Duration.between(start, end));
        Assertions.assertEquals(7, employeeDBService.retrieveDBData(EmployeeDBService.IO_Service.DB_IO).size());
    }
}
