package com.example.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.Project;
import com.example.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  public Project saveOrUpdateProject(Project project) {

    return projectRepository.save(project);
  }
}
