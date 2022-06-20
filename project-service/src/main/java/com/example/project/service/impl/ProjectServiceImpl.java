package com.example.project.service.impl;

import com.example.project.client.BudgetClient;
import com.example.project.client.ContractorClient;
import com.example.project.client.PropietarioClient;
import com.example.project.entity.Project;
import com.example.project.model.Budget;
import com.example.project.model.Contractor;
import com.example.project.model.Propietario;
import com.example.project.service.ProjectService;
import com.example.project.repository.ProjectRepository;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    BudgetClient budgetClient;

    @Autowired
    PropietarioClient propietarioClient;

    @Autowired
    ContractorClient contractorClient;

    @Override
    public List<Project> ListAllProjects() {return projectRepository.findAll();}

    @Override
    public Project getProject(long id) {
        Project project= projectRepository.findById(id).orElse(null);
        if (null != project ){
            Propietario propietario = propietarioClient.getPropietario(project.getPropietarioId()).getBody();
            Budget budget = budgetClient.getBudget(project.getBudgetId()).getBody();
            Contractor contractor = contractorClient.getContractor(project.getContractorId()).getBody();
            project.setBudget(budget);
            project.setContractor(contractor);
            project.setPropietario(propietario);
        }
        return project;
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
