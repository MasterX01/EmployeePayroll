package com.bridgelabz.employeepayroll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeePayrollDB {
    private Connection connection = new ConnectionFile().dbConnection();
    public EmployeePayrollDB() throws SQLException, ClassNotFoundException {
    }

    public List<EmployeeDBData> retrieveData() throws SQLException {
        String query = "SELECT * FROM employee_payroll;";
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


    public int updateSalary(String name, double salary) throws SQLException {
        String query = "UPDATE employee_payroll SET salary=? WHERE name=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, salary);
        preparedStatement.setString(2, name);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public List<EmployeeDBData> retrieveInDateRange(String startDate, String endDate) throws SQLException {
        String query = "SELECT * FROM employee_payroll WHERE start BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, startDate);
        preparedStatement.setString(2, endDate);
        List<EmployeeDBData> employeeDBDataList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double salary = resultSet.getDouble("salary");
            String gender = resultSet.getString("gender");
            LocalDate date = resultSet.getDate("start").toLocalDate();
            employeeDBDataList.add(new EmployeeDBData(id, name, salary, gender, date));
        }
        connection.close();
        return employeeDBDataList;
    }

    public double retrieveTotalSalaryBasedOnGender(String gender) throws SQLException {
        String query = "SELECT SUM(salary) FROM employee_payroll WHERE gender=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return resultSet.getDouble("salary");
    }

    public double retrieveAvgSalaryBasedOnGender(String gender) throws SQLException {
        String query = "SELECT AVG(salary) FROM employee_payroll WHERE gender=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return resultSet.getDouble("salary");
    }

    public double retrieveMinSalaryBasedOnGender(String gender) throws SQLException {
        String query = "SELECT MIN(salary) FROM employee_payroll WHERE gender=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return resultSet.getDouble("salary");
    }

    public double retrieveMaxSalaryBasedOnGender(String gender) throws SQLException {
        String query = "SELECT MAX(salary) FROM employee_payroll WHERE gender=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return resultSet.getDouble("salary");
    }

    public int retrieveCountOfGender(String gender) throws SQLException {
        String query = "SELECT COUNT(salary) FROM employee_payroll WHERE gender=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }
}
