package com.bridgelabz.employeepayroll;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDBService {
    public enum IO_Service{DB_IO}
    public List<EmployeeDBData> employeeDBDataList;

    public List<EmployeeDBData> retrieveDBData(IO_Service io_service) throws SQLException, ClassNotFoundException {
        if(io_service.equals(IO_Service.DB_IO)){
            this.employeeDBDataList = new EmployeePayrollDB().retrieveData();
        }
        return employeeDBDataList;
    }
}
