package com.tcs.ins.pms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.tcs.ins.pms.service.model.ProjectDetailModel;
import com.tcs.ins.pms.service.model.ProjectDetailSearchRequest;

public interface PMSService {

	ProjectDetailModel getProject(Long id);

	Page<ProjectDetailModel> search(PageRequest pageRequest, ProjectDetailSearchRequest searchRequest);

	ProjectDetailModel createProject(ProjectDetailModel projectDetailModel);

	ProjectDetailModel updateProject(ProjectDetailModel projectDetailModel);

	void deleteProject(Long id);
}
