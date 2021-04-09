package com.bridgelabz.employeepayroll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EmployeeDBService {

    public enum IO_Service {DB_IO}

    public List<EmployeeDBData> employeeDBDataList;

    public List<EmployeeDBData> retrieveDBData(IO_Service io_service) throws SQLException, ClassNotFoundException {
        if (io_service.equals(IO_Service.DB_IO)) {
            this.employeeDBDataList = new EmployeePayrollDB().retrieveData();
        }
        return employeeDBDataList;
    }

    public EmployeeDBData updateSalary(String name, double salary) throws SQLException, ClassNotFoundException, InterruptedException {
        int result = new EmployeePayrollDB().updateSalary(name, salary);
        if (result == 1) {
            this.employeeDBDataList = new EmployeePayrollDB().retrieveData();
            for (int i = 0; i < employeeDBDataList.size(); i++) {
                EmployeeDBData emp = employeeDBDataList.get(i);
                if (emp.name.equals(name)) {
                    EmployeeDBData employeeSalary = new EmployeeDBData(emp.getEmpID(), emp.getName(), emp.getSalary(), emp.getGender(), emp.getStart());
                    return employeeSalary;
                }
            }
        }
        return null;
    }

    public List<EmployeeDBData> retrieveDataInDateRange(String startDate, String endDate) throws SQLException, ClassNotFoundException {
        return new EmployeePayrollDB().retrieveInDateRange(startDate, endDate);
    }
}
