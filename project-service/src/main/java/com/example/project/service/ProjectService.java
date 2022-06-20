package com.example.project.service;

import com.example.project.entity.Project;
import java.util.List;

public interface ProjectService {
    public List<Project> ListAllProjects();
    public Project getProject(long id);
    public Project createProject(Project project);
    public Project updateProject(Project project);
    public Project deleteProject(long id);
}