package com.example.ppmtool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.Valid;
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
  private Date startDate;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date endDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date createAt;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date updateAt;

  public Project() {
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

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public Date getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(Date updateAt) {
    this.updateAt = updateAt;
  }

  @PrePersist
  protected void onCreate() {
    this.createAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updateAt = new Date();
  }


}
