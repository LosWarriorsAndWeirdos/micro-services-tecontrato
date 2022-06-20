package com.example.project.service.impl;

import com.example.project.entity.Project;
import com.example.project.service.ProjectService;
import com.example.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> ListAllProjects() {return projectRepository.findAll();}

    @Override
    public Project getProject(long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        Project projectDB = getProject(project.getId());
        if (projectDB == null){
            return null;
        }
        projectDB.setStatus(project.getStatus());
        projectDB.setDescription(project.getDescription());
        projectDB.setProject(project.getProject());

        return projectRepository.save(projectDB);
    }

    @Override
    public Project deleteProject(long id) {
        Project projectDB = getProject(id);
        if (projectDB == null){
            return null;
        }
        return projectRepository.save(projectDB);
    }

}
