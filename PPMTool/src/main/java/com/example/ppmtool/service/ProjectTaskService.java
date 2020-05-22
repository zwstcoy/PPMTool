package com.example.ppmtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ppmtool.domain.Backlog;
import com.example.ppmtool.domain.Project;
import com.example.ppmtool.domain.ProjectTask;
import com.example.ppmtool.exceptions.ProjectIdException;
import com.example.ppmtool.exceptions.ProjectNotFoundException;
import com.example.ppmtool.repository.BacklogRepository;
import com.example.ppmtool.repository.ProjectRepository;
import com.example.ppmtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

  @Autowired
  private ProjectTaskRepository projectTaskRepository;

  @Autowired
  private BacklogRepository backlogRepository;

  @Autowired
  private ProjectRepository projectRepository;


  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
    //Pts add to a project, project != null, bl is not null
    // set backlog to projectTask

    try {
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
      return projectTaskRepository.save(projectTask);
    } catch (Exception e) {
      throw new ProjectNotFoundException("Project with Id: " + projectIdentifier + ", not Found");
    }


  }

  public Iterable<ProjectTask> findBacklogById(String backlog_id) {
    Project project = projectRepository.findByProjectIdentifier(backlog_id);
    if (project == null) {
      throw new ProjectNotFoundException("Project with Id: " + backlog_id + ", not Found");
    }
    Iterable<ProjectTask> projectTasks = projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    return projectTasks;
  }

  public ProjectTask findProjectTaskByProjectSequence(String backlog_id, String sequence) {
    Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
    if (backlog == null) {
      throw new ProjectNotFoundException("Project with Id: " + backlog_id + ", not Found");
    }

    ProjectTask projectTask = projectTaskRepository.findProjectTaskByProjectSequence(sequence);
    if (projectTask == null) {
      throw new ProjectNotFoundException("Project Task with Id: " + sequence + ", not Found");
    }
    if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
      throw new ProjectNotFoundException("Project Task with Id: " + sequence + " doesn't in the Project " + backlog_id);

    }
    return projectTask;
  }


  public ProjectTask updateByProjectSequence(ProjectTask updateTask, String backlog_id, String sequence) {
    ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, sequence);

    projectTask = updateTask;

    return projectTaskRepository.save(projectTask);
  }

  public void deleteProjectTaskBySequence(String backlog_id, String sequence) {
    ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, sequence);

    Backlog backlog = projectTask.getBacklog();
    List<ProjectTask> projectTasks = projectTask.getBacklog().getProjectTasks();
    projectTasks.remove(projectTask);
    backlogRepository.save(backlog);

    projectTaskRepository.delete(projectTask);
  }
}
