package com.example.ppmtool.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProjectTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(updatable = false)
  private String projectSequence;
  @NotBlank(message = "Please include a project summary")
  private String summary;
  private String acceptCriteria;
  private String status;
  private Integer priority;
  @Column(updatable = false)
  private String projectIdentifier;
  //many to one to backlog

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date dueDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  @Column(updatable = false)
  private Date create_At;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date update_At;

  @PrePersist
  protected void onCreate() {
    this.create_At = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.update_At = new Date();
  }

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
  @JsonIgnore
  private Backlog backlog;

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProjectSequence() {
    return projectSequence;
  }

  public void setProjectSequence(String projectSequence) {
    this.projectSequence = projectSequence;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getAcceptCriteria() {
    return acceptCriteria;
  }

  public void setAcceptCriteria(String acceptCriteria) {
    this.acceptCriteria = acceptCriteria;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
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

  @Override
  public String toString() {
    return "ProjectTask{" +
        "id=" + id +
        ", projectSequence='" + projectSequence + '\'' +
        ", summary='" + summary + '\'' +
        ", acceptCriteria='" + acceptCriteria + '\'' +
        ", status='" + status + '\'' +
        ", priority=" + priority +
        ", projectIdentifier='" + projectIdentifier + '\'' +
        ", dueDate=" + dueDate +
        ", create_At=" + create_At +
        ", update_At=" + update_At +
        '}';
  }
}
