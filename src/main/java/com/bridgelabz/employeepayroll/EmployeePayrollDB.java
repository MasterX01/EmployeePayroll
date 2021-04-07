package com.bridgelabz.employeepayroll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDB {
    private Connection connection = new ConnectionFile().dbConnection();
    public EmployeePayrollDB() throws SQLException, ClassNotFoundException {
    }

    public List<EmployeeDBData> retrieveData() throws SQLException, ClassNotFoundException {
        String query = "Select * from employee_payroll;";
        List<EmployeeDBData> employeeDBDataList = new ArrayList<>();
        Statement statement = connection.createStatement();
        try{
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                String gender = resultSet.getString("gender");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                employeeDBDataList.add(new EmployeeDBData(id, name, salary, gender, date));
            }
        }catch (SQLException e){ }
        connection.close();
        return  employeeDBDataList;
    }
}
