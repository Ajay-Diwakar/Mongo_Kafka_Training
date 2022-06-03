package com.virtusa.empservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.empservice.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	
	Employee findByEmpId(Long empId);

}
