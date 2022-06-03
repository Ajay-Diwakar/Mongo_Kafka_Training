package com.virtusa.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.department.entity.Department;
import com.virtusa.department.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
//		log.info("Inside save department DepartmentService");
		return departmentRepository.save(department);
	}

	public Department findDeparmentById(Long departmentId) {
//		log.info("inside get department by idDepartmentService");
		return departmentRepository.findByDepartmentId(departmentId);
	}
	
	

}
