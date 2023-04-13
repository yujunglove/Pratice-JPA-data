package com.greedy.jpadata.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")			
@SequenceGenerator(
		name="SEQ_EMP_ID_GENERATOR",		
		sequenceName="SEQ_EMP_ID",		
		initialValue=100,					
		allocationSize=1					
)
public class Employee {
	
	@Id									
	@Column(name="EMP_ID")			
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,		
			generator="SEQ_EMP_ID_GENERATOR"		
	)
	private String empId;
	
	@Column(name="EMP_NAME")	
	private String empName;
	
	@Column(name="EMP_NO")	
	private String empNo;
	
	@Column(name="EMAIL")	
	private String email;
	
	@Column(name="PHONE")	
	private String phone;
	
	@Column(name="DEPT_CODE")	
	private String deptCode;
	
	@Column(name="JOB_CODE")	
	private String jobCode;
	
	@Column(name="SALARY")	
	private int salary;

	public Employee() {
	
	}

	public Employee(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, int salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salary = salary;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
				+ ", phone=" + phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salary=" + salary + "]";
	}

	

}
