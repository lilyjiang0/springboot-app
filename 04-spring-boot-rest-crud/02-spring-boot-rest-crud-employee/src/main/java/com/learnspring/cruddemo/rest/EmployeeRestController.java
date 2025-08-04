package com.learnspring.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learnspring.cruddemo.entity.Employee;
import com.learnspring.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    // Quick and dirty inject employee dao
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    // Expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // Add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return employee;
    }

    // Add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // Also just in case they pass an id in JSON ... set id to 0.
        // This is to force a save of new item ... instead of update.
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // Add mapping for update existing employee
    // note: put will replace the entire resource
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // Add mapping for PATCH /employees/{employeeId} - patch employee ... partial update
    // note: Put modifies only specific parts of resource
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {
        Employee dbEmployee = employeeService.findById(employeeId);
        // Throw exception if null.
        if (dbEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        // Throw exception if request body contains "id".
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }

        Employee patchEmployee = apply(patchPayload, dbEmployee);
        Employee newDbEmployee = employeeService.save(patchEmployee);
        return newDbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee dbEmployee) {
        // Convert employee object to a JSON object node.
        ObjectNode employeeNode = objectMapper.convertValue(dbEmployee, ObjectNode.class);
        // Convert the patchPayload map to a JSON object node.
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        // Merge the patch updates in to the employee node.
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    // Add mapping for DELETE employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        // Throw exception if null.
        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
