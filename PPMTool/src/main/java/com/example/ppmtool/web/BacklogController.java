package com.example.ppmtool.web;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppmtool.domain.Backlog;
import com.example.ppmtool.domain.Project;
import com.example.ppmtool.domain.ProjectTask;
import com.example.ppmtool.service.MapValidationErrorService;
import com.example.ppmtool.service.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

  @Autowired
  private ProjectTaskService projectTaskService;

  @Autowired
  private MapValidationErrorService mapValidationErrorService;

  @PostMapping("/{backlog_id}")
  public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
      @PathVariable String backlog_id, BindingResult result) {

    ResponseEntity<?> errorRespond = mapValidationErrorService.MapValidationErrorService(result);
    if (errorRespond != null) {
      return errorRespond;
    }
    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
    return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.CREATED);
  }

  @GetMapping("/{backlog_id}")
  public Iterable<?> getProjectTBacklog(@PathVariable String backlog_id) {
    return projectTaskService.findBacklogById(backlog_id);
  }

  @GetMapping("/{backlog_id}/{pta}")
  public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pta) {
    ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backlog_id, pta);
    return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
  }

  @PatchMapping("/{backlog_id}/{pta}")
  public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
      @PathVariable String backlog_id, @PathVariable String pta) {
    ResponseEntity<?> errorRespond = mapValidationErrorService.MapValidationErrorService(result);
    if (errorRespond != null) {
      return errorRespond;
    }
    ProjectTask updateProject = projectTaskService.updateByProjectSequence(projectTask, backlog_id, pta);
    return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
  }


  @DeleteMapping("/{backlog_id}/{pta}")
  public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pta) {
    projectTaskService.deleteProjectTaskBySequence(backlog_id, pta);
    return new ResponseEntity<String>("Project Task with ID: " + pta + " is been delete", HttpStatus.OK);
  }
}
