package com.bridgelabz.employeepayroll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDBService {

    public enum IO_Service {DB_IO}

    public List<EmployeeDBData> employeeDBDataList = new ArrayList<EmployeeDBData>();

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

    public double getTotalSalaryForGender(String gender) throws SQLException, ClassNotFoundException {
        return new EmployeePayrollDB().retrieveTotalSalaryBasedOnGender(gender);
    }

    public double getAvgSalaryForGender(String gender) throws SQLException, ClassNotFoundException {
        return new EmployeePayrollDB().retrieveAvgSalaryBasedOnGender(gender);
    }

    public double getMinSalaryForGender(String gender) throws SQLException, ClassNotFoundException {
        return new EmployeePayrollDB().retrieveMinSalaryBasedOnGender(gender);
    }

    public double getMaxSalaryForGender(String gender) throws SQLException, ClassNotFoundException {
        return new EmployeePayrollDB().retrieveMaxSalaryBasedOnGender(gender);
    }

    public int getCountGender(String gender) throws SQLException, ClassNotFoundException {
        return new EmployeePayrollDB().retrieveCountOfGender(gender);
    }

    public List<EmployeeDBData> addNewEmployee(String name,double salary,String gender,String start, String dept) throws ClassNotFoundException, SQLException{
        EmployeeDBData emp = null;
        int id = new EmployeePayrollDB().addNewEmployee(name, salary, gender, start, dept);
        ResultSet resultSet = new EmployeePayrollDB().retrieveSingleEmployee(id);
        while(resultSet.next()){
            if(resultSet.getInt("id") == id){
                int empID = resultSet.getInt("id");
                String empName = resultSet.getString("name");
                double empSalary = resultSet.getDouble("salary");
                String gen = resultSet.getString("gender");
                LocalDate empDate = resultSet.getDate("start").toLocalDate();
                emp = new EmployeeDBData(empID, empName, empSalary, gen, empDate);
            }
        }
        this.employeeDBDataList.add(emp);
        return employeeDBDataList;
    }

    public int deleteEmployee(String name) throws ClassNotFoundException, SQLException{
        return new EmployeePayrollDB().deleteEmployee(name);
    }
}
