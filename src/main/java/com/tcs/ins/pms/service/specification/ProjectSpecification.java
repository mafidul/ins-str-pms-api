package com.tcs.ins.pms.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.tcs.ins.pms.repository.entity.ProjectDetail;
import com.tcs.ins.pms.service.model.ProjectDetailSearchRequest;

public class ProjectSpecification implements Specification<ProjectDetail> {

	private static final long serialVersionUID = 1L;
	private final ProjectDetailSearchRequest projectDetailSearchRequest;

	public ProjectSpecification(ProjectDetailSearchRequest projectDetailSearchRequest) {
		this.projectDetailSearchRequest = projectDetailSearchRequest;
	}

	@Override
	public Predicate toPredicate(Root<ProjectDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		if (projectDetailSearchRequest.idFilteringRequired()) {
			predicates.add(criteriaBuilder.equal(root.get("id"), projectDetailSearchRequest.getId()));
		}
		if (projectDetailSearchRequest.projectNameFilteringRequired()) {
			predicates.add(
					criteriaBuilder.like(root.get("projectName"), projectDetailSearchRequest.getProjectName() + "%"));
		}
		if (projectDetailSearchRequest.customerFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("customer"), projectDetailSearchRequest.getCustomer() + "%"));
		}
		if (projectDetailSearchRequest.projectLeadFilteringRequired()) {
			predicates.add(
					criteriaBuilder.like(root.get("projectLead"), projectDetailSearchRequest.getProjectLead() + "%"));
		}
		
		return andTogether(predicates, criteriaBuilder);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}
}
