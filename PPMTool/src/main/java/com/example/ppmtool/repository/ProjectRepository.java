package com.example.ppmtool.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

  @Override
  List<Project> findAllById(Iterable<String> iterable);
}
