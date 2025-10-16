package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Add mapping for listing employees.
    @GetMapping("/list")
    public String listEmployees(Model model) {
        // Get employees from db.
        List<Employee> employees = employeeService.findAll();
        // Add to the spring model.
        model.addAttribute("employees", employees);

        return "employees/list-employees";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        // Create model attribute to bind form data.
        Employee employee = new Employee();
        model.addAttribute(employee);
        return "employees/save-employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model) {
        // Get the employee from the service.
        Employee employee = employeeService.findById(employeeId);
        // Set employee in the model to prepopulate the form.
        model.addAttribute("employee", employee);
        // Send over to our form.
        return "employees/save-employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // Save the employee.
        employeeService.save(employee);
        // Use a redirect to prevent duplicate submissions.
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        // Delete the employee.
        employeeService.deleteById(employeeId);
        // Redirect to the /employees/list.
        return "redirect:/employees/list";
    }
}
