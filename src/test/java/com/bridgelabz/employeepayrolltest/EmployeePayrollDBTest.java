package com.bridgelabz.employeepayrolltest;

import com.bridgelabz.employeepayroll.EmployeeDBData;
import com.bridgelabz.employeepayroll.EmployeeDBService;
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
}
