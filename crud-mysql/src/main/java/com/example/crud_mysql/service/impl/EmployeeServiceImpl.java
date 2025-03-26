package com.example.crud_mysql.service.impl;

import com.example.crud_mysql.exception.ResourceNotFoundException;
import com.example.crud_mysql.model.Employee;
import com.example.crud_mysql.repository.EmployeeRepository;
import com.example.crud_mysql.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //Instance Variable - An instance of the EmployeeRepository interface.
    //Spring's (DI) mechanism provides an implementation of the interface at runtime.
    private EmployeeRepository employeeRepository;

    //Constructor for employeeRepository variable.  (Constructor based Dependency Injection)
    //@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }
    //saves employee data in the database.
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    //gets all the employees data.
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    //get employee data by id.
    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
        //Using Lambda Expression
        //return employeeRepository.findById(id).orElseThrow(() ->
        //        new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //Check if the employee exists in the DB.
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));

        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());

        //save to DB.
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));

        employeeRepository.deleteById(id);
    }

}