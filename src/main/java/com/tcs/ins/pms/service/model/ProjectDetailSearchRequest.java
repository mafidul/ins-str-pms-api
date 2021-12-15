package com.tcs.ins.pms.service.model;

import java.util.Map;

import org.springframework.util.StringUtils;

public class ProjectDetailSearchRequest {
	private static final String REQUEST_PARAM_ID = "id";
	private static final String REQUEST_PARAM_NAME = "projectName";
	private static final String REQUEST_PARAM_LEAD = "projectLead";
	private static final String REQUEST_PARAM_CUSTOMER = "customer";
	private static final String REQUEST_PARAM_STATUS = "status";

	private final Long id;

	private final String projectName;
	private final String projectLead;
	private final String customer;
	private final String status;

	public ProjectDetailSearchRequest(Map<String, String> requestParam) {
		String idStr = requestParam.get(REQUEST_PARAM_ID);
		if (StringUtils.hasText(idStr)) {
			this.id = Long.valueOf(idStr);
		} else {
			this.id = null;
		}
		this.projectName = requestParam.get(REQUEST_PARAM_NAME);
		this.projectLead = requestParam.get(REQUEST_PARAM_LEAD);
		this.customer = requestParam.get(REQUEST_PARAM_CUSTOMER);
		this.status = requestParam.get(REQUEST_PARAM_STATUS);
	}

	public boolean idFilteringRequired() {
		return id != null;
	}

	public boolean projectNameFilteringRequired() {
		return StringUtils.hasText(projectName);
	}

	public boolean projectLeadFilteringRequired() {
		return StringUtils.hasText(projectLead);
	}

	public boolean customerFilteringRequired() {
		return StringUtils.hasText(customer);
	}

	public boolean statusFilteringRequired() {
		return StringUtils.hasText(status);
	}

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
}
