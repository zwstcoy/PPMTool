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


  public ProjectTask addProjectTask(ProjectTask projectTask){
    //Pts add to a project, project != null, bl is not null
    // set backlog to projectTask

//    projectTask.setBacklog(backlog);
//    projectTask.setPriority();
//
//    Backlog backlog = backlogRepository.findByProjectIdentifier(projectTask.getProjectIdentifier().toUpperCase());
//    backlog.getProjectTasks().add(projectTask);
//    backlog.setPTSequence();

    return projectTask;
  }
}
