package com.example.ppmtool.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Project name is require")
  private String projectName;

  @NotBlank(message = "Project identifier is require")
  @Size(min = 4, max = 4, message = "Range between 4 - 5 characters")
  @Column(updatable = false, unique = true)
  private String projectIdentifier;
  @NotBlank(message = "Project description is require")
  private String description;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date start_date;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date end_date;

  @JsonFormat(pattern = "yyyy-mm-dd")
  @Column(updatable = false)
  private Date create_At;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date update_At;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
  //
  private Backlog backlog;

  public Project() {
  }

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStart_date() {
    return start_date;
  }

  public void setStart_date(Date start_date) {
    this.start_date = start_date;
  }

  public Date getEnd_date() {
    return end_date;
  }

  public void setEnd_date(Date endDate) {
    this.end_date = endDate;
  }


  public Date getCreate_At() {
    return create_At;
  }

  public void setCreate_At(Date create_At) {
    this.create_At = create_At;
  }

  public Date getUpdate_At() {
    return update_At;
  }

  public void setUpdate_At(Date update_At) {
    this.update_At = update_At;
  }

  @PrePersist
  protected void onCreate() {
    this.create_At = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.update_At = new Date();
  }


}
