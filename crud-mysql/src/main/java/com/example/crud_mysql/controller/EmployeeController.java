package com.example.crud_mysql.controller;

import com.example.crud_mysql.model.Employee;
import com.example.crud_mysql.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    //inject dependency.
    private EmployeeService employeeService;

    //constructor for the dependency.
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Create data in DB REST API.   (CREATE)
    //Endpoint : http://localhost:8080/api/employees
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee> (employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //Get All Employees REST API.   (READ)
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //Get Employee by Id.       (READ)
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long empId){
        return new ResponseEntity<>(employeeService.getEmployeeById(empId),HttpStatus.OK);
    }

    //Update the Employee data in the DB.   (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }

    //Delete an Employee using ID.      (DELETE)
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>("Employee Deleted Successfully",HttpStatus.OK);
    }


//    @DeleteMapping("{id}")
//    public void deleteEmployee(@PathVariable("id") long id){
//        employeeService.deleteEmployee(id);
//    }

}
