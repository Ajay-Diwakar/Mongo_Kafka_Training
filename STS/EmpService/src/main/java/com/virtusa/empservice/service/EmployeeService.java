package com.virtusa.empservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.empservice.VO.Department;
import com.virtusa.empservice.VO.ResponseTemplateVO;
import com.virtusa.empservice.entity.Employee;
import com.virtusa.empservice.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;

	public Employee saveUser(Employee emp) {
		return empRepository.save(emp);
	}

public ResponseTemplateVO getEmpWithDepartment(Long empId) {
		
//		log.info("Inside get getEmpWithDepartment from employee service");
		
		ResponseTemplateVO vo = new ResponseTemplateVO();
		
		Employee employee =  empRepository.findByEmpId(empId);
		
		Department department = 
				restTemplate.getForObject(
						"http://localhost:8081/departments/"+ employee.getDepartmentId(), 
						Department.class
						);
		vo.setEmployee(employee);
		vo.setDepartment(department);
		
		return vo;
	}

}
