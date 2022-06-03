package com.virtusa.empservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.empservice.VO.ResponseTemplateVO;
import com.virtusa.empservice.entity.Employee;
import com.virtusa.empservice.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService emService;
	
	@PostMapping("/")
	public Employee saveUser(@RequestBody Employee emp) {
		return emService.saveUser(emp);
	}
	
	@GetMapping("/{id}")
	public ResponseTemplateVO getEmpWithDepartment(@PathVariable("id") Long empId) {
//		log.info("Inside get Employee with department from Employee Controller");
		return emService.getEmpWithDepartment(empId);
	}
	
}
