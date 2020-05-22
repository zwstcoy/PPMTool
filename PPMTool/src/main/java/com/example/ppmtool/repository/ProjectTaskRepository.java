package com.example.ppmtool.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ppmtool.domain.Project;
import com.example.ppmtool.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

  Iterable<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

  ProjectTask findProjectTaskByProjectSequence(String sequence);
}
