package com.example.project.repository;

import com.example.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findByCreationDate(String date);
}
