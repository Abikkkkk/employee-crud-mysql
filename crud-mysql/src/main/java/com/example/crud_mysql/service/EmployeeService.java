package com.example.crud_mysql.service;

import com.example.crud_mysql.model.Employee;

import java.util.List;

public interface EmployeeService {

    //abstract methods.
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);

}
