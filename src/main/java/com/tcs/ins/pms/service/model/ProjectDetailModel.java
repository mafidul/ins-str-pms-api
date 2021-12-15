package com.tcs.ins.pms.service.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class ProjectDetailModel {

	private Long id;

	@NotBlank(message = "Project name is Mandatory")
	private String projectName;
	@NotBlank(message = "Project Lead is Mandatory")
	private String projectLead;
	@NotBlank(message = "Customer is Mandatory")
	private String customer;
	@NotBlank(message = "Status is Mandatory")
	private String status;
	@NotBlank(message = "Priority is Mandatory")
	private String priority;
	@NotBlank(message = "Percent Complete is Mandatory")
	private String percentComplete;
	@NotBlank(message = "Project Summary is Mandatory")
	private String projectSummary;

	//@NotBlank(message = "Start Date is Mandatory")
	private LocalDateTime startDate;
	//@NotBlank(message = "End Date is Mandatory")
	private LocalDateTime endDate;

	public Long getId() {
		return id;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectLead() {
		return projectLead;
	}

	public String getCustomer() {
		return customer;
	}

	public String getStatus() {
		return status;
	}

	public String getPriority() {
		return priority;
	}

	public String getPercentComplete() {
		return percentComplete;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setPercentComplete(String percentComplete) {
		this.percentComplete = percentComplete;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}

	@Override
	public String toString() {
		return "ProjectDetailModel [id=" + id + ", projectName=" + projectName + ", projectLead=" + projectLead
				+ ", customer=" + customer + ", status=" + status + ", priority=" + priority + ", percentComplete="
				+ percentComplete + ", projectSummary=" + projectSummary + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
}
