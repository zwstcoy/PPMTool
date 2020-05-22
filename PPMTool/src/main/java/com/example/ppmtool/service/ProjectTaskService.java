package com.example.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.Backlog;
import com.example.ppmtool.domain.ProjectTask;
import com.example.ppmtool.repository.BacklogRepository;
import com.example.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

  @Autowired
  private ProjectTaskRepository projectTaskRepository;

  @Autowired
  private BacklogRepository backlogRepository;


  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
    //Pts add to a project, project != null, bl is not null
    // set backlog to projectTask
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
    projectTask.setBacklog(backlog);
    backlog.getProjectTasks().add(projectTask);

    Integer backSequence = backlog.getPTSequence();
    backSequence++;

    projectTask.setProjectSequence(projectIdentifier.toUpperCase() + "-" + backSequence);
    projectTask.setProjectIdentifier(projectIdentifier);
    backlog.setPTSequence(backSequence);

    if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
      projectTask.setPriority(3);
    }

    if (projectTask.getStatus() == null || projectTask.getStatus().equals("")) {
      projectTask.setStatus("TO_DO");
    }
    return projectTask;
  }
}
