package com.greedy.jpadata.menu.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greedy.jpadata.employee.repository.EmployeeRepository;
import com.greedy.jpadata.menu.dto.EmployeeDTO;
import com.greedy.jpadata.menu.dto.JobDTO;
import com.greedy.jpadata.menu.entity.Employee;
import com.greedy.jpadata.menu.entity.Job;

import org.springframework.data.domain.Sort;


@Service
public class EmployeeService {

	//의존성 주입 = 왜?
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;

	public EmployeeService( EmployeeRepository employeeRepository,ModelMapper modelMapper ){
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	   public EmployeeDTO findEmployeeByCode(String empId) {
		      
		      Employee emp = employeeRepository.findById(empId).orElseThrow(IllegalArgumentException::new);
		      
		      return modelMapper.map(emp, EmployeeDTO.class);
		   }
	   
	 //2. findAll - >페이징 처리 전
	   public List<EmployeeDTO> findEmployeeList() {
		    List<Employee> empList = employeeRepository.findAll(Sort.by("empId").descending());
		    return empList.stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
		}
	   
		//3, Page -> 페이징 처리 후
		public Page<EmployeeDTO> findEmployeeList(Pageable pageable) {
			
			//page 파라미터가 Pageable의 number 값으로 넘어오는데 해당 값이 조회시에는 인텍스 기준이 되어야 해서 -1 처리가 필요하다
			//만약 0보다 작거나 같다면, 
			pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, 
					pageable.getPageSize(), 
					Sort.by("empId").descending());

			//페이지라는 타입은 본인이 map이라는 메소드를 가지고 있어서 조금 더 간략하게 변환이 가능하다.
			Page<Employee> empList = employeeRepository.findAll(pageable);

			//컨트롤러로 돌아간다.
			return empList.map(emp -> modelMapper.map(emp,EmployeeDTO.class));
		}

		//QueryMethod
		/* 4. QueryMethod */
		public List<EmployeeDTO> menuTest1(Integer salary) {
			List<EmployeeDTO> empList = employeeRepository.findBySalaryGreaterThan(salary, Sort.by("salary").descending());
			return empList.stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
		}
		
		//5. JPQL or native Query
		//5. JPQL or native Query
		public List<JobDTO> findAllJob() {
		    List<Job> jobList = employeeRepository.findAllJob();
		    return jobList.stream().map(job -> modelMapper.map(job, JobDTO.class)).collect(Collectors.toList());
		}


		
		
		
		//6. save
		@Transactional
		public void registNewEmp(EmployeeDTO emp) {
			// TODO Auto-generated method stub
			employeeRepository.save(modelMapper.map(emp, Employee.class));
			
		}

		@Transactional
		public void deleteMenu(String empId) {
			employeeRepository.deleteById(empId);
			
		}






	   
	


}
