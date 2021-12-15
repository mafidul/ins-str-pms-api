package com.tcs.ins.pms.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.tcs.ins.pms.repository.entity.ProjectDetail;
import com.tcs.ins.pms.service.model.ProjectDetailModel;

@Mapper(componentModel = "spring")
public interface PMSMapper {
	ProjectDetailModel toModel(ProjectDetail source);
	ProjectDetail toEntity(ProjectDetailModel source);
	List<ProjectDetailModel> toModel(Page<ProjectDetail> page);
}
