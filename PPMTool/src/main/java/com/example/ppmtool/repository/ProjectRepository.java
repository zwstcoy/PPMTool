package com.example.ppmtool.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {

  Project findByProjectIdentifier(String projectId);

  @Override
  Iterable<Project> findAll();
}
