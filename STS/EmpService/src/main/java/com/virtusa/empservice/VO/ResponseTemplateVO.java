package com.virtusa.empservice.VO;
import com.virtusa.empservice.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

	private Employee employee;
	private Department department;
	
}