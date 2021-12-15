package com.tcs.ins.pms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcs.ins.pms.exception.ApplicationException;
import com.tcs.ins.pms.repository.PMSRepository;
import com.tcs.ins.pms.repository.entity.ProjectDetail;
import com.tcs.ins.pms.service.mapper.PMSMapper;
import com.tcs.ins.pms.service.model.ProjectDetailModel;
import com.tcs.ins.pms.service.model.ProjectDetailSearchRequest;
import com.tcs.ins.pms.service.specification.ProjectSpecification;


@Service
public class DefaultPMSService implements PMSService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPMSService.class);

	private final PMSRepository pMSRepository;
	private final PMSMapper pMSMapper;

	public DefaultPMSService(PMSRepository pMSRepository, PMSMapper pMSMapper) {
		this.pMSRepository = pMSRepository;
		this.pMSMapper = pMSMapper;
	}

	// Project Details
	@Override
	public ProjectDetailModel getProject(Long id) {
		ProjectDetail projectDetail = getOrThrow(id);
		return pMSMapper.toModel(projectDetail);
	}

	private ProjectDetail getOrThrow(Long id) {
		Optional<ProjectDetail> OptionalprojectDetail = pMSRepository.findById(id);
		if (!(OptionalprojectDetail.isPresent())) {
			throw ApplicationException.noRecordFound("Project not found");
		}
		return OptionalprojectDetail.get();
	}

	@Override
	public ProjectDetailModel createProject(ProjectDetailModel projectDetailModel) {
		ProjectDetail projectDetail = pMSMapper.toEntity(projectDetailModel);
		ProjectDetail saveProjectDetail = pMSRepository.save(projectDetail);
		return pMSMapper.toModel(saveProjectDetail);
	}

	@Override
	public ProjectDetailModel updateProject(ProjectDetailModel projectDetailModel) {
		ProjectDetail projectDetail = getOrThrow(projectDetailModel.getId());
		if (StringUtils.hasText(projectDetail.getCustomer())) {
			projectDetail.setCustomer(projectDetailModel.getCustomer());
		}
		if (StringUtils.hasText(projectDetail.getPercentComplete())) {
			projectDetail.setPercentComplete(projectDetailModel.getPercentComplete());
		}
		if (StringUtils.hasText(projectDetail.getPriority())) {
			projectDetail.setPriority(projectDetailModel.getPriority());
		}
		if (StringUtils.hasText(projectDetail.getProjectLead())) {
			projectDetail.setProjectLead(projectDetailModel.getProjectLead());
		}
		if (StringUtils.hasText(projectDetail.getProjectName())) {
			projectDetail.setProjectName(projectDetailModel.getProjectName());
		}
		if (StringUtils.hasText(projectDetail.getProjectSummary())) {
			projectDetail.setProjectSummary(projectDetailModel.getProjectSummary());
		}
		if (StringUtils.hasText(projectDetail.getStatus())) {
			projectDetail.setStatus(projectDetailModel.getStatus());
		}
		projectDetail.setStartDate(projectDetailModel.getStartDate());
		projectDetail.setEndDate(projectDetailModel.getEndDate());
		ProjectDetail saveProjectDetail = pMSRepository.save(projectDetail);
		return pMSMapper.toModel(saveProjectDetail);
	}

	@Override
	public void deleteProject(Long id) {
		pMSRepository.deleteById(id);
	}

	// Project details pagination
	@Override
	public Page<ProjectDetailModel> search(PageRequest pageRequest, ProjectDetailSearchRequest searchRequest) {
		Page<ProjectDetail> page = pMSRepository.findAll(new ProjectSpecification(searchRequest), pageRequest);
		List<ProjectDetailModel> content = pMSMapper.toModel(page);
		return new PageImpl<>(content, pageRequest, page.getTotalElements());
	}
}
