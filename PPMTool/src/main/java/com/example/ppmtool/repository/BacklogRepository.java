package com.example.ppmtool.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ppmtool.domain.Backlog;
import com.example.ppmtool.domain.Project;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

}
