package com.example.project.controller;


import com.example.project.entity.Project;
import com.example.project.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> listAllProjects(){
        List<Project> projects = new ArrayList<>();
        projects = projectService.ListAllProjects();
        if (projects.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projects);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id){
        log.info("Obeteniendo el projecto con id {}", id);
        Project project = projectService.getProject(id);
        if (project == null){
            log.error("El proyecto con id {} no existe,", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project, BindingResult result){
        log.info("Creando projecto {}", project);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Project projectDB = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") long id, @RequestBody Project project){
        log.info("Actualizando projecto con id {}", id);
        Project currentProject = projectService.getProject(id);
        if(currentProject == null){
            log.error("No se puede actualizar. Project con id {} no fue encontrado", id);
            return ResponseEntity.notFound().build();
        }
        currentProject = projectService.updateProject(project);
        return ResponseEntity.ok(currentProject);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
