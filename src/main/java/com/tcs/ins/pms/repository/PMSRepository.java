package com.tcs.ins.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tcs.ins.pms.repository.entity.ProjectDetail;

@Repository
public interface PMSRepository extends JpaRepository<ProjectDetail, Long>, JpaSpecificationExecutor<ProjectDetail> {

}
