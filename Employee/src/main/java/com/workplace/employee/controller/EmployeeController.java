package com.workplace.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workplace.employee.model.Employee;
import com.workplace.employee.service.EmployeeService;
import com.workplace.employee.util.Validate;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return this.employeeService.getAllEmployees();
	}

	@GetMapping("/getEmployee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return this.employeeService.getEmployee(id);
	}

	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody Employee employee) {
		Validate.notNull(employee);
		final List<Employee> existingEmployees = this.employeeService.getAllEmployees();
		if (!existingEmployees.contains(employee)) {
			this.employeeService.saveEmployee(employee);
			return "Emplyee has successfully added";
		}
		return "Employee already exists";
	}

	@PutMapping("/editEmployee/{id}")
	public String editEmployee(@PathVariable int id, @RequestBody Employee employee) {
		Validate.notNull(employee);
		this.employeeService.saveEmployee(employee);
		return "Employee has successfully updated";
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable int id) {
		this.employeeService.deleteEmployee(id);
	}
}
