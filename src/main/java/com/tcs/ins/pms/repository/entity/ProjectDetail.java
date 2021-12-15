package com.tcs.ins.pms.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "PROJECTDETAIL")
@Audited
public class ProjectDetail extends Auditable implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PROJECTNAME")
	private String projectName;
	@Column(name = "PROJECTLEAD")
	private String projectLead;
	@Column(name = "CUSTOMER")
	private String customer;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PRIORITY")
	private String priority;
	@Column(name = "PERCENTAGECOMPLETE")
	private String percentComplete;
	@Column(name = "PROJECTSUMMARY")
	private String projectSummary;

	@Column(name = "STARTDATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate;
	@Column(name = "ENDDATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public String getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}
}
