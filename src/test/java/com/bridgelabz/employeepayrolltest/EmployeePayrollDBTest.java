package com.bridgelabz.employeepayrolltest;

import com.bridgelabz.employeepayroll.EmployeeDBData;
import com.bridgelabz.employeepayroll.EmployeeDBService;
import com.bridgelabz.employeepayroll.EmployeePayrollDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
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
}
