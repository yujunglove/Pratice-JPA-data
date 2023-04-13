package com.greedy.jpadata.menu.dto;

public class JobDTO {
	private String jobCode;
	private String jabName;
	public JobDTO() {
	
	}
	public JobDTO(String jobCode, String jabName) {
		super();
		this.jobCode = jobCode;
		this.jabName = jabName;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJabName() {
		return jabName;
	}
	public void setJabName(String jabName) {
		this.jabName = jabName;
	}
	@Override
	public String toString() {
		return "JobDTO [jobCode=" + jobCode + ", jabName=" + jabName + "]";
	}

	
}
