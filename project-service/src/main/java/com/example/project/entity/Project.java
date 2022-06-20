package com.example.project.entity;


import com.example.project.model.Budget;
import com.example.project.model.Contractor;
import com.example.project.model.Propietario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no debe estar vacío.")
    private String project;
    @Column(name="CREATIONDATE")
    private String creationDate;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="STATUS")
    private Boolean status;
    @Column(name="budget_id")
    private Long budgetId;

    @Column(name="contractor_id")
    private Long contractorId;
    @Column(name="propietario_id")
    private Long propietarioId;


    @Transient
    private Propietario propietario;

    @Transient
    private Budget budget;

    @Transient
    Contractor contractor;



}
