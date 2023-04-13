package com.greedy.jpadata.employee.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.jpadata.menu.dto.EmployeeDTO;
import com.greedy.jpadata.menu.entity.Employee;
import com.greedy.jpadata.menu.entity.Job;


public interface EmployeeRepository extends JpaRepository<Employee, String> {


    @Query(value = "SELECT JOB_CODE, JOB_NAME FROM JOB"
            , nativeQuery = true)
	List<Job> findAllJob();
	List<EmployeeDTO> findBySalaryGreaterThan(Integer salary, Sort sort);


}

